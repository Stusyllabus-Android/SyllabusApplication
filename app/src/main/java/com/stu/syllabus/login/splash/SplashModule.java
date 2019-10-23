package com.stu.syllabus.login.splash;

import dagger.Module;
import dagger.Provides;

/**
 * yuan
 * 2019/10/23
 **/
@Module
public class SplashModule {
    private final SplashContract.view mView;

    public SplashModule(SplashContract.view view) {
        mView = view;
    }

    @Provides
    SplashContract.view provideView() {
        return mView;
    }
}
