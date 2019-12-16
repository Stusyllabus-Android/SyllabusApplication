package com.stu.syllabus.home.wirelessData;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.bean.WirelessInfo;
import com.stu.syllabus.retrofitApi.SchoolInternetApi;
import com.stu.syllabus.widget.StreamItemLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class WirelessActivity extends BaseActivity {

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
    @BindView(R.id.switchAccountButton)
    ImageButton mSwitchAccountButton;
    @BindView(R.id.openInternetLoginSwitch)
    SwitchCompat mOpenInternetLoginSwitch;

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
        setToolBarTitle();

/*        mLoginButton.setOnClickListener(new View.OnClickListener() {
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
        });*/

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

        mSwitchAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchAccont();
            }
        });

        if (InternetModel.getInstance().getLoginAccounts().size() != 0) {
            selectAccount(InternetModel.getInstance().getLoginAccounts().get(0));
        }

        /**
         * 实时监控
         */
        mOpenInternetLoginSwitch.setChecked(InternetModel.getInstance().isOpen());
        if (InternetModel.getInstance().isOpen()) {
            mInternetInfoCardView.setVisibility(View.VISIBLE);
        } else {
            mInternetInfoCardView.setVisibility(View.GONE);
        }

        /**
         * 实时监控的按钮状态改变
         */
        mOpenInternetLoginSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                InternetModel.getInstance().setOpen(isChecked);
//                EventBus.getDefault().post(new InternetModel(isChecked));
                if (isChecked) {
                    mInternetInfoCardView.setVisibility(View.VISIBLE);
                } else {
                    mInternetInfoCardView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void switchAccont() {

        String[] accouts = InternetModel.getInstance().getAccountName();
        if (accouts.length == 0) {
            Toast.makeText(this,"无切换账号",Toast.LENGTH_SHORT).show();
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

    }

    private void selectAccount(InternetModel.LoginAccount loginAccount) {
        mUsernameEditText.setText(loginAccount.account);
        mPasswordEditText.setText(loginAccount.password);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_stream_wireless;
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
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }
}
