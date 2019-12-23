package com.stu.syllabus.person.personal;

import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.person.PersonModule;

import javax.inject.Inject;

import butterknife.BindView;

public class PersonalActivity extends BaseActivity implements PersonalContract.view, View.OnClickListener{

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.toChangeAvatar)
    RelativeLayout toChangeAvatar;
    @BindView(R.id.headImageDraweeView)
    SimpleDraweeView headImageView;
    @BindView(R.id.accountTextView)
    TextView accountTextView;
    @BindView(R.id.toChangeNickName)
    RelativeLayout toChangeNickName;
    @BindView(R.id.nicknameTextView)
    TextView nicknameTextView;
    @BindView(R.id.toChangeSignature)
    RelativeLayout toChangeSignature;
    @BindView(R.id.signatureTextView)
    TextView signatureTextView;

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
        personalPresenter.init();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_personal;
    }

    @Override
    protected void init() {
        super.init();
        setupTitleBar(toolbar);
        toChangeAvatar.setOnClickListener(this);
        toChangeNickName.setOnClickListener(this);
        toChangeSignature.setOnClickListener(this);
    }

    @Override
    public void loadInfo(String avatar, String account, String nickname, String signature) {
        headImageView.setImageURI(avatar);
        accountTextView.setText(account);
        nicknameTextView.setText(nickname);
        signatureTextView.setText(signature);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, PersonalActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toChangeAvatar: toChangeAct(new Intent(this, UpdateAvatarActivity.class)); break;
            case R.id.toChangeNickName: toChangeAct(new Intent(this, UpdateNicknameActivity.class)); break;
            case R.id.toChangeSignature: toChangeAct(new Intent(this, UpdateSignatureActivity.class)); break;
        }
    }

    @Override
    public void toChangeAct(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
}
