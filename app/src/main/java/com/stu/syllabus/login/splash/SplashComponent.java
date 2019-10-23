package com.stu.syllabus.login.splash;

import com.stu.syllabus.AppComponent;

import dagger.Component;

/**
 * yuan
 * 2019/10/23
 **/
@Component(dependencies = AppComponent.class, modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
