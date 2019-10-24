package com.stu.syllabus.login.splash;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.UtilModule;
import com.stu.syllabus.di.PerActivity;
import com.stu.syllabus.login.LoginModule;

import dagger.Component;

/**
 * yuan
 * 2019/10/23
 **/
@PerActivity
@Component(dependencies = AppComponent.class,  modules = {SplashModule.class, LoginModule.class})
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
