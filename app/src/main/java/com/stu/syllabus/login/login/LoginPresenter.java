package com.stu.syllabus.login.login;

import android.util.Log;

import com.stu.syllabus.bean.Authorize;
import com.stu.syllabus.bean.Login;
import com.stu.syllabus.bean.Oauth;
import com.stu.syllabus.bean.Skey;
import com.stu.syllabus.login.ILoginModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * yuan
 * 2019/10/23
 **/
public class LoginPresenter implements LoginContract.presenter{

    private String TAG = this.getClass().getSimpleName();

    LoginContract.view mView;
    ILoginModel mILoginModel;

    Oauth mAauth;
    Login mLogin;
    Authorize mAuthorize;
    Skey mSkey;

    @Inject
    public LoginPresenter(LoginContract.view view, ILoginModel iLoginModel) {
        mView = view;
        mILoginModel = iLoginModel;
    }

    @Override
    public void init() {

    }

    @Override
    public void login(final String account, String password) {

        //TODO: 2019/10/23 调用model实现登录,成功则进入主界面
        mILoginModel.getOauthFromNet()
        .subscribe(new Observer<Oauth>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Oauth oauth) {
                mAauth = oauth;
                Log.d(TAG, "onNext: " + "getOauthFromNet");
                Log.d(TAG, "onNext: " + oauth.getState());
                Log.d(TAG, "onNext: " + oauth.getClient_id());
                Log.d(TAG, "onNext: " + oauth.getScope());
                Log.d(TAG, "onNext: " + oauth.getCode());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        mILoginModel.getLoginFromNet(account, password)
        .subscribe(new Observer<Login>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Login login) {
                mLogin = login;
                Log.d(TAG, "onNext: " + "getLoginFromNet");
                Log.d(TAG, "onNext: " + login.getCode());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        mILoginModel.getAuthorizeCodeFromNet("code", mAauth.getClient_id(), mAauth.getState(), mAauth.getScope(), "android" )
        .subscribe(new Observer<Authorize>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Authorize authorize) {
                mAuthorize = authorize;
                Log.d(TAG, "onNext: " + "getAuthorizeCodeFromNet");
                Log.d(TAG, "onNext: " + authorize.getAuthorization_code());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        mILoginModel.getSkeyFromNet(mAuthorize.getAuthorization_code(), mAauth.getState(), "android")
        .subscribe(new Observer<Skey>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Skey skey) {
                mSkey = skey;
                Log.d(TAG, "onNext: " + "getSkeyFromNet");
                Log.d(TAG, "onNext: " + skey.getSkey());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
