package com.stu.syllabus.home.wirelessData;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.bean.WireLoginInfo;
import com.stu.syllabus.bean.WirelessInfo;
import com.stu.syllabus.retrofitApi.SchoolInternetApi;
import com.stu.syllabus.widget.StreamItemLayout;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import cn.icheny.view.CySwitch;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class WirelessActivity extends BaseActivity implements WirelessContract.view{

    @BindView(R.id.usernameEditText)
    EditText mUsernameEditText;
    @BindView(R.id.passwordEditText)
    EditText mPasswordEditText;
    @BindView(R.id.loginButton)
    Button mLoginButton;
    @BindView(R.id.logoutButton)
    Button mLogoutButton;

    @BindView(R.id.isRememberCheckBox)
    CheckBox mIsRememberCheckBox;
    /*@BindView(R.id.switchAccountButton)
    ImageButton mSwitchAccountButton;*/
    @BindView(R.id.openInternetLoginSwitch)
    CySwitch mOpenInternetLoginSwitch;

    AlertDialog mSwitchAccountDialog;
    AlertDialog mLoadingDialog;

    @BindView(R.id.internetInfoCardView)
    CardView mInternetInfoCardView;

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.loginInfoTextView)
    TextView mLoginInfoTextView;
    @BindView(R.id.usernameItem)
    StreamItemLayout mUsernameItem;
    @BindView(R.id.nowStreamItem)
    StreamItemLayout mNowStreamItem;
    @BindView(R.id.allStreamItem)
    StreamItemLayout mAllStreamItem;
    @BindView(R.id.outTimeItem)
    StreamItemLayout mOutTimeItem;
    @BindView(R.id.stateItem)
    StreamItemLayout mStateItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerWirelessComponent.builder()
                .appComponent(appComponent)
                .wirelessModule(new WirelessModule(this))
                .build()
                .inject(this);

        setToolBarTitle();
        /*mLoadingDialog = LoadingDialogBuiler.getLoadingDialog(
                this, ThemeUtil.getInstance().colorPrimary
        );*/

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mLoginInfoTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        updateState();
                    }
                });
            }
        }, 0, 2000);

//        mSwitchAccountButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchAccont();
//            }
//        });

        if (InternetModel.getInstance().getLoginAccounts().size() != 0) {
            selectAccount(InternetModel.getInstance().getLoginAccounts().get(0));
        }

        /**
         * 实时监控
         */
        mOpenInternetLoginSwitch.setViewRadius(5);
        mOpenInternetLoginSwitch.setSliderRadius(5);
        mOpenInternetLoginSwitch.setBorderWidth(4);
        mOpenInternetLoginSwitch.setChecked(InternetModel.getInstance().isOpen());
        if (InternetModel.getInstance().isOpen()) {
            mInternetInfoCardView.setVisibility(View.VISIBLE);
        } else {
            mInternetInfoCardView.setVisibility(View.GONE);
        }

        /**
         * 实时监控的按钮状态改变
         */
        mOpenInternetLoginSwitch.setOnCheckedChangeListener(new CySwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CySwitch buttonView, boolean isChecked) {
                InternetModel.getInstance().setOpen(isChecked);
                EventBus.getDefault().post(new InternetOpenEvent(isChecked));
                if (isChecked) {
                    mInternetInfoCardView.setVisibility(View.VISIBLE);
                } else {
                    mInternetInfoCardView.setVisibility(View.GONE);
                }
            }
        });
    }

    /*private void switchAccont() {

        String[] accouts = InternetModel.getInstance().getAccountName();
        if (accouts.length == 0) {
            Toast.makeText(WirelessActivity.this,"无切换账号",Toast.LENGTH_SHORT).show();
            return;
        }
        mSwitchAccountDialog = new AlertDialog.Builder(this)
                .setTitle("切换账号")
                .setItems(InternetModel.getInstance().getAccountName(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectAccount(InternetModel.getInstance().getLoginAccounts().get(which));
                    }
                }).create();
        mSwitchAccountDialog.show();

    }*/

    private void selectAccount(InternetModel.LoginAccount loginAccount) {
        mUsernameEditText.setText(loginAccount.account);
        mPasswordEditText.setText(loginAccount.password);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_wireless;
    }

    private void login() {
//        mLoadingDialog.show();

        SchoolInternetApi schoolInternetApi = InternetModel.getInstance().mRetrofit.create(SchoolInternetApi.class);

        final WireLoginInterService interService = InternetModel.getInstance().mRetrofit.create(
                WireLoginInterService.class
        );

        final String username = mUsernameEditText.getText().toString().trim();
        final String password = mPasswordEditText.getText().toString().trim();

        schoolInternetApi.getInternetInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        Document doc = Jsoup.parse(s);
                        Element tables = doc.getElementsByTag("table").first();
                        Elements trs = tables.select("tr");

                        //少于2证明没登陆
                        if (trs.size() < 2) {
                            return true;
                        }
                        Toast.makeText(WirelessActivity.this,"用户已在线",Toast.LENGTH_SHORT).show();

                        return false;
//                        SnackbarUtil.ShortSnackbar(mLoginButton, "用户已在线", SnackbarUtil.Info).show();
                    }
                }).observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) {
                        return interService.loginInternet(
                                "pwdLogin",
                                username,
                                password,
                                mIsRememberCheckBox.isChecked() ? "1" : "0"
                        );
                    }

                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, Object>() {
                    @Override
                    public WireLoginInfo apply(String s) {
                        return GsonUtil.getDefault().fromJson(
                                s.replaceAll("'", "\""), WireLoginInfo.class
                        );
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                        public void onComplete() {
//                       mLoadingDialog.dismiss();
                       updateState();
                   }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        /*if (wireLoginInfo.isSuccess()) {
                            InternetModel.LoginAccount account = new InternetModel.LoginAccount();
                            account.account = username;
                            account.password = password;
                            InternetModel.getInstance().addAccount(account);
                        }*/
                        Toast.makeText(WirelessActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {
//                        SnackbarUtil.ShortSnackbar(mLoginButton, "登录失败", SnackbarUtil.Alert).show();
                       Toast.makeText(WirelessActivity.this, "登录失败", Toast.LENGTH_SHORT).show();

                       updateState();
//                       mLoadingDialog.dismiss();
                   }
                   })
                ;
    }

    private void logout() {
//        mLoadingDialog.show();

        SchoolInternetApi schoolInternetApi = InternetModel.getInstance().mRetrofit.create(SchoolInternetApi.class);

        final WireLoginInterService interService = InternetModel.getInstance().mRetrofit.create(
                WireLoginInterService.class
        );

        schoolInternetApi.getInternetInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        Document doc = Jsoup.parse(s);
                        Element tables = doc.getElementsByTag("table").first();
                        Elements trs = tables.select("tr");

                        //少于2证明没登陆
                        if (trs.size() < 2) {
                            return false;
                        }
                        Toast.makeText(WirelessActivity.this, "用户已登出",Toast.LENGTH_SHORT).show();
                        mUsernameEditText.setText("");
                        mPasswordEditText.setText("");
//                      SnackbarUtil.ShortSnackbar(mLoginButton, "用户已登出", SnackbarUtil.Info).show();
                        return true;
                    }
                }).observeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public Observable<String> apply(String s) {
                        return interService.loginInternet(
                                "logout",
                                "",
                                "",
                                ""
                        );
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, Object>() {
                    @Override
                    public WireLoginInfo apply(String s) {
                        return GsonUtil.getDefault().fromJson(
                                s.replaceAll("'", "\""), WireLoginInfo.class
                        );
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onComplete() {
                       /* mLoadingDialog.dismiss();
                        mLoginInfoTextView.setText("用户已登出");*/
                    }

                    @Override
                    public void onError(Throwable e) {
//                        Toast.makeText(WirelessActivity.this, "注销失败",Toast.LENGTH_SHORT).show();
//                        SnackbarUtil.ShortSnackbar(mLoginButton, "注销失败", SnackbarUtil.Alert).show();
                        Toast.makeText(WirelessActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        mLoadingDialog.dismiss();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("WireAcitivity logout__", "onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        Toast.makeText(WirelessActivity.this, "登录成功",Toast.LENGTH_SHORT).show();
//                        SnackbarUtil.ShortSnackbar(mLoginButton, loginInfo.getMsg(), SnackbarUtil.Info).show();

                    }
                });

    }

    private void updateState() {
        WirelessInfo wirelessInfo = WirelessInfo.getInstance();
        if (wirelessInfo.getType() == WirelessInfo.TYPE_SUCCESS) {
            mLoginInfoTextView.setText("用户已登录");
            mUsernameItem.setStreamInfo(wirelessInfo.getName());
            mNowStreamItem.setStreamInfo(wirelessInfo.getNowStream());
            mAllStreamItem.setStreamInfo(wirelessInfo.getAllStream());
            mOutTimeItem.setStreamInfo(wirelessInfo.getOutTime());
            mStateItem.setStreamInfo(wirelessInfo.getState());
        } else {
            mUsernameItem.setStreamInfo("");
            mNowStreamItem.setStreamInfo("");
            mAllStreamItem.setStreamInfo("");
            mOutTimeItem.setStreamInfo("");
            mStateItem.setStreamInfo("");
            if (wirelessInfo.getType() == WirelessInfo.TYPE_LOGOUT) {
                mLoginInfoTextView.setText("当前没登陆流量验证");
            } else if (wirelessInfo.getType() == WirelessInfo.TYPE_UN_CONNECT) {
                mLoginInfoTextView.setText("没接入校园网");
            }
        }
    }

    public void setToolBarTitle() {
        super.setupTitleBar(toolbar);
    }
}
