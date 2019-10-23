package com.stu.syllabus.login.splash;

import com.stu.syllabus.login.ILoginModel;

/**
 * yuan
 * 2019/10/23
 **/
public class SplashPresenter implements SplashContract.presenter {

    SplashContract.view mView;
    ILoginModel mLoginModel;

    public SplashPresenter(SplashContract.view view, ILoginModel iLoginModel) {
        mView = view;
        mLoginModel = iLoginModel;
    }

    @Override
    public void init() {
        // TODO: 2019/10/23 从数据库中查询，是否存在skey
    }
}
