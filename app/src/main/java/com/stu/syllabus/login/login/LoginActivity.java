package com.stu.syllabus.login.login;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stu.syllabus.HttpMethods;
import com.stu.syllabus.ToastUtil;
import com.stu.syllabus.main.MainActivity;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.bean.Login;
import com.stu.syllabus.bean.Oauth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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

    LoginContract.presenter mLoginPresenter;
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter();
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
        HttpMethods.getInstance().getOauth(new Observer<Oauth>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Oauth oauth) {
                Log.d(TAG, "onNext: " + oauth.getClient_id() + " " + oauth.getState());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        HttpMethods.getInstance().login(new Observer<Login>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Login login) {
                Log.d(TAG, "onNext: " + login.getCode());
                if (login.getCode().equals("0")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Log.d(TAG, "onNext: " + "finish");
                    LoginActivity.this.finish();
                } else Toast.makeText(LoginActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, accountEditText.getText().toString(), passwordEditText.getText().toString());
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
