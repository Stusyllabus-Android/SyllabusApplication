package com.stu.syllabus.login.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.stu.syllabus.ToastUtil;
import com.stu.syllabus.main.MainActivity;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * yuan
 * 2019/10/22
 **/
public class LoginActivity extends BaseActivity implements LoginContract.view{

    @BindView(R.id.accountEditText)
    EditText accountEditText;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;
    @BindView(R.id.loginButton)
    Button loginButton;

    @Inject
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);

        mLoginPresenter.init();
    }

    @Override
    protected int getContentView() {
        return R.layout.acticity_login;
    }

    @OnClick(R.id.loginButton)
    public void setLogin() {
        if (accountEditText.getText().toString().isEmpty()) {
            ToastUtil.showShort(this, "账号不能为空");
            return;
        } else if (passwordEditText.getText().toString().isEmpty()) {
            ToastUtil.showShort(this, "密码不能为空");
            return;
        }
        Log.d(TAG, "setLogin: ");
        mLoginPresenter.login(accountEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @Override
    public void toMainView() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }

    @Override
    public void showLoading(boolean isShow) {

    }

    @Override
    public void showFailMessage(String msg) {

    }
}
