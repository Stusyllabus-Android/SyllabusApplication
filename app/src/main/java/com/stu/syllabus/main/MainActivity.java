package com.stu.syllabus.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.RetrofitModule;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.bean.WirelessInfo;
import com.stu.syllabus.di.HasComponent;
import com.stu.syllabus.home.wirelessData.IWirelessModel;
import com.stu.syllabus.home.wirelessData.InternetModel;
import com.stu.syllabus.home.wirelessData.WireService;
import com.stu.syllabus.home.wirelessData.WirelessModel;
import com.stu.syllabus.retrofitApi.SchoolInternetApi;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * yuan
 * 2019/10/22
 **/
public class MainActivity extends BaseActivity implements MainContract.view, HasComponent<AppComponent> {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    MainPresenter mainPresenter;

    IWirelessModel iWirelessModel;

    private AppComponent mAppComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAppComponent = appComponent;

        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build();
        mainPresenter = new MainPresenter(this);

        /**
         * 流量跟踪
         */
        Retrofit retrofit = appComponent.getWirelessRetrofit();
        final SchoolInternetApi schoolInternetApi = retrofit.create(SchoolInternetApi.class);
        iWirelessModel = new WirelessModel(schoolInternetApi);

        if (InternetModel.getInstance().isOpen()) {
            startStreamListen();
        } else {
            stopStreamListen();
        }
        //end流量跟踪

        mainPresenter.init();

    }

    @Override
    public AppComponent getComponent() {
        return mAppComponent;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initBottomNavigationView(final List<Fragment> fragmentList) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                onTabItemSelected(fragmentList, menuItem.getItemId());
                return true;
            }
        });
    }

    @Override
    public void onTabItemSelected(List<Fragment> fragmentList, int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.home:
                fragment = fragmentList.get(0);
                break;
            case R.id.information:
                fragment = fragmentList.get(1);
                break;
            case R.id.syllabus:
                fragment = fragmentList.get(2);
                break;
            case R.id.contact:
                fragment = fragmentList.get(3);
                break;
            case R.id.person:
                fragment = fragmentList.get(4);
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: ");
        return false;
    }

    /**
     * 流量监听
     */
    boolean isActive;
    Timer mTimer;
    private void startStreamListen() {
        isActive = true;
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                iWirelessModel.getWirelessInfo()
                        .subscribe(new Consumer<WirelessInfo>() {
                            @Override
                            public void accept(WirelessInfo wirelessInfo){
                                if (InternetModel.getInstance().isOpen() && isActive) {
                                    Intent intent = WireService.getIntent(MainActivity.this);
                                    startService(intent);
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                if (InternetModel.getInstance().isOpen() && isActive) {
                                    Intent intent = WireService.getIntent(MainActivity.this);
                                    startService(intent);
                                }
                            }
                        });
            }
        }, 0, 3000);
    }

    private void stopStreamListen() {
        isActive = false;
        if (mTimer != null) {
            mTimer.cancel();
            Intent intent = WireService.getIntent(MainActivity.this);
            stopService(intent);
        }
    }

}
