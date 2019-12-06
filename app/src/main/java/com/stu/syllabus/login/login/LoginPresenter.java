package com.stu.syllabus.login.login;

import android.util.Log;

import com.stu.syllabus.bean.Authorize;
import com.stu.syllabus.bean.Login;
import com.stu.syllabus.bean.Oauth;
import com.stu.syllabus.bean.Skey;
import com.stu.syllabus.bean.UserInfo;
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
    public void login(final String account, final String password) {
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
                                        if (mLogin.getCode().equals("0")) {
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
                                                            if (authorize.getCode().equals("0")) {
                                                                Log.d(TAG, "onNext: " + mAuthorize.getAuthorization_code() + " " + mAauth.getState());
                                                                mILoginModel.getSkeyFromNet(mAuthorize.getAuthorization_code(), mAauth.getState(), "android")
                                                                        .subscribe(new Observer<Skey>() {
                                                                            @Override
                                                                            public void onSubscribe(Disposable d) {

                                                                            }

                                                                            @Override
                                                                            public void onNext(final Skey skey) {
                                                                                Log.d(TAG, "onNext: " + "getSkeyFromNet");
                                                                                try {
                                                                                    if (skey.getSkey()!= null && !skey.getSkey().isEmpty()) {
                                                                                        Log.d(TAG, "onNext: " + skey.getSkey());
                                                                                        mSkey = skey;
                                                                                        mILoginModel.getUserInfoFromNet(skey.getSkey(), "/user/info", "get", "android")
                                                                                                .subscribe(new Observer<UserInfo>() {
                                                                                                    @Override
                                                                                                    public void onSubscribe(Disposable d) {

                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onNext(UserInfo userInfo) {
                                                                                                        //一些持久化操作
                                                                                                        mILoginModel.saveSkeyToDisk(skey.getSkey(), skey.getRefresh_key());
                                                                                                        mILoginModel.saveUserBaseInfoToDisk(account, password);
                                                                                                        mILoginModel.saveUserInfoToDisk(account, userInfo.getData().getUser().getInfo().getAvatar(), userInfo.getData().getUser().getInfo().getNickname(), userInfo.getData().getUser().getInfo().getSignature(), "Non-existent");
                                                                                                        mView.toMainView();
                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onError(Throwable e) {

                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onComplete() {

                                                                                                    }
                                                                                                });
                                                                                    }
                                                                                } catch (Exception e) {
                                                                                    e.printStackTrace();
                                                                                    // TODO: 2019/11/18 空对象出错处理
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onError(Throwable e) {
                                                                                e.printStackTrace();
                                                                            }

                                                                            @Override
                                                                            public void onComplete() {

                                                                            }
                                                                        });
                                                            }
                                                        }

                                                        @Override
                                                        public void onError(Throwable e) {
                                                            Log.d(TAG, "onError: " + mAauth.getClient_id() + " " + mAauth.getState() + " " + mAauth.getScope());
                                                            e.printStackTrace();
                                                            Log.d(TAG, "onError: "+ "getAuthorizecodeError");
                                                        }

                                                        @Override
                                                        public void onComplete() {
                                                            Log.d(TAG, "onComplete: " + "getAuthorizecodeComplete");
                                                        }
                                                    });
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        e.printStackTrace();
                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.d(TAG, "onComplete: " + "getLoginComplete");
                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + "getOauth");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}