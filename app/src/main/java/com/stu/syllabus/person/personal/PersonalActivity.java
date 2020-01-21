package com.stu.syllabus.person.personal;

import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.person.PersonModule;
import com.stu.syllabus.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalActivity extends BaseActivity implements PersonalContract.view{

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.headImageDraweeView)
    SimpleDraweeView headImageView;
    @BindView(R.id.accountTextView)
    TextView accountTextView;
    @BindView(R.id.nicknameEditText)
    EditText nicknameEditText;
    @BindView(R.id.signatureEditText)
    EditText signatureEditText;
    @BindView(R.id.cancelFAButton)
    FloatingActionButton cancelFAButton;
    @BindView(R.id.sureFAButton)
    FloatingActionButton sureFAButton;

    private String avatar;
    private String signature;
    private String nickname;

    @Inject
    PersonalPresenter personalPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerPersonalComponent.builder()
                .appComponent(appComponent)
                .personalModule(new PersonalModule(this))
                .build()
                .inject(this);
//        personalPresenter.init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        personalPresenter.init();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_personal;
    }

    @Override
    protected void init() {
        super.init();
        setupTitleBar(toolbar);
    }

    @Override
    public void loadInfo(String avatar, String account, String nickname, String signature) {
        this.avatar = avatar;
        this.nickname = nickname;
        this.signature = signature;
        headImageView.setImageURI(avatar);
        accountTextView.setText(account);
        nicknameEditText.setText(nickname);
        if (signature != null && !signature.isEmpty()) {
            signatureEditText.setText(signature);
        }
    }

    @OnClick({R.id.cancelFAButton, R.id.sureFAButton})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancelFAButton: finish(); break;
            case R.id.sureFAButton:
                if (nickname.equals(nicknameEditText.getText().toString()))
                    nickname = null;
                if (signature.equals(signatureEditText.getText().toString()))
                    signature = null;
                personalPresenter.updateUserInfo(avatar, nickname, signature); break;
        }
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, PersonalActivity.class);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
}
