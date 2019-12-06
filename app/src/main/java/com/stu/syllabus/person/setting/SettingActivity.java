package com.stu.syllabus.person.setting;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.stu.syllabus.App;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.widget.ShareAppDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements SettingContract.view, ShareAppDialog.OnShareSelectCallBack{

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.versionNameTextView)
    TextView versionNameTextView;

    @Inject
    SettingPresenter settingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSettingComponent.builder()
                .appComponent(appComponent)
                .settingModule(new SettingModule(this))
                .build()
                .inject(this);

        settingPresenter.init();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }

    public static Intent getIntent(Context context) {
        Intent mIntent = new Intent();
        mIntent.setClass(context, SettingActivity.class);
        return mIntent;
    }

    @Override
    public void init() {
        Log.d(TAG, "init: " + App.versionName);
        Log.d(TAG, "init: " + App.versionCode);
        setupTitleBar(toolbar);
        versionNameTextView.setText("版本" + App.versionName);
    }

    @OnClick({R.id.helpAndFeedback, R.id.recommendTo, R.id.aboutUs, R.id.logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.helpAndFeedback: startActivity(HelpAndFeedbackActivity.getIntent(this));break;
            case R.id.recommendTo: settingPresenter.recommendTo(); break;
            case R.id.aboutUs: startActivity(AboutActivity.getIntent(this));break;
            case R.id.logout: settingPresenter.logout(); break;
        }
    }

    @Override
    public void setupTitleBar(Toolbar toolbar) {
        super.setupTitleBar(toolbar);
    }

    @Override
    public void showShareDialog() {
        ShareAppDialog shareAppDialog = new ShareAppDialog();
        shareAppDialog.setOnShareSelectCallBack(this);
        shareAppDialog.show(getSupportFragmentManager());
    }

    private void share(int scene) {

    }

    @Override
    public void onShareSelect(int position) {
        // TODO: 2019/11/26 点击分享 
    }

    @Override
    public void toLoginActivity() {
        Intent intent = new Intent();
        intent.putExtra("finishMainActivity", "finishMainActivity");
        setResult(1000, intent);
        this.finish();
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
}