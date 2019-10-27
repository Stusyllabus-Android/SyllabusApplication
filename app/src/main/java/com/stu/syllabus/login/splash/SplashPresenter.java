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
        mLoginModel.getSkeyFromDisk()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        if (s.equals("Non-existent")) {
                            mView.toLoginView();
                        } else mView.toMainView();
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
