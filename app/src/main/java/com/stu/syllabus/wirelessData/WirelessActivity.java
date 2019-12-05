package com.stu.syllabus.wirelessData;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.bean.WirelessInfo;
import com.stu.syllabus.widget.StreamItemLayout;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class WirelessActivity extends BaseActivity {

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

    @BindView(R.id.internetInfoCardView)
    CardView mInternetInfoCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        toolbar.setTitle(R.string.home_wireless_data);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }
}
