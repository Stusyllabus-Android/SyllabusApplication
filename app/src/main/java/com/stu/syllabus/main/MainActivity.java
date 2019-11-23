package com.stu.syllabus.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.di.HasComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * yuan
 * 2019/10/22
 **/
public class MainActivity extends BaseActivity implements MainContract.view, HasComponent<AppComponent> {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    MainPresenter mainPresenter;

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
}
