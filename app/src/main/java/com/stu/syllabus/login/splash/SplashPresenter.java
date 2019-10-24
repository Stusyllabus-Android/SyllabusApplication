package com.stu.syllabus.login.splash;

import android.util.Log;

import com.stu.syllabus.bean.Skey;
import com.stu.syllabus.di.PerActivity;
import com.stu.syllabus.login.ILoginModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * yuan
 * 2019/10/23
 **/
public class SplashPresenter implements SplashContract.presenter {

    private String TAG = this.getClass().getSimpleName();

    SplashContract.view mView;
    ILoginModel mLoginModel;

    @Inject
//    @PerActivity
    public SplashPresenter(SplashContract.view view, ILoginModel iLoginModel) {
        mView = view;
        mLoginModel = iLoginModel;
    }

    @Override
    public void init() {
        // TODO: 2019/10/24 如果获取到的skey为空则去登录界面，否则进入主界面
//        mLoginModel.getSkeyFromDisk()
//                .subscribe(new Observer<Skey>() {
//                    boolean isGet = false;
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Skey skey) {
//                        Log.d(TAG, "onNext: " + skey.getSkey());
//                        if (!skey.getSkey().isEmpty()) {
//                            isGet = true;
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        if (isGet) {
//                            mView.toMainView();
//                        } else mView.toLoginView();
//                    }
//                });
        mView.toLoginView();
    }
}
