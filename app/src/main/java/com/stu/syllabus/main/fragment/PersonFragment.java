package com.stu.syllabus.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.login.login.LoginActivity;
import com.stu.syllabus.person.DaggerPersonComponent;
import com.stu.syllabus.person.PersonContract;
import com.stu.syllabus.person.PersonModule;
import com.stu.syllabus.person.PersonPresenter;
import com.stu.syllabus.person.personal.PersonalActivity;
import com.stu.syllabus.person.setting.SettingActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * yuan
 * 2019/10/22
 **/
public class PersonFragment extends BaseFragment implements PersonContract.view {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.settingLayout)
    RelativeLayout settingLayout;
    @BindView(R.id.headImageDraweeView)
    SimpleDraweeView headImageDraweeView;
    @BindView(R.id.nicknameTextView)
    TextView nicknameTextView;
    @BindView(R.id.idTextView)
    TextView idTextView;
    @BindView(R.id.signatureTextView)
    TextView signatureTextView;
    @BindView(R.id.toPersonalActivity)
    LinearLayout toPersonalActivity;

    private AppComponent appComponent;

    @Inject
    PersonPresenter personPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = this.getComponent(AppComponent.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerPersonComponent.builder()
                .appComponent(appComponent)
                .personModule(new PersonModule(this))
                .build()
                .inject(this);

        personPresenter.init();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_person;
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.person);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }

    @OnClick({R.id.settingLayout, R.id.toPersonalActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settingLayout: startActivityForResult(SettingActivity.getIntent(getContext()), 1000);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); break;
            case R.id.toPersonalActivity: startActivity(PersonalActivity.getIntent(getContext()));
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null) return;
        if (requestCode == 1000) {
            String value = data.getStringExtra("finishMainActivity");
            if(value.equals("finishMainActivity")){
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        }
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void init(String id, String avatar, String nickname, String signature) {
        idTextView.setText("账号：" + id);
        headImageDraweeView.setImageURI(avatar);
        nicknameTextView.setText(nickname);
        if (!signature.isEmpty() && signature != null) {
            signatureTextView.setText(signature);
        }
    }
}
