package com.stu.syllabus.login.login;

import dagger.Module;
import dagger.Provides;

/**
 * yuan
 * 2019/10/23
 **/
@Module
public class LoginModule {
    private final LoginContract.view mView;

    public LoginModule(LoginContract.view view) {
        mView = view;
    }

    @Provides
    LoginContract.view provideView() {
        return mView;
    }
}
