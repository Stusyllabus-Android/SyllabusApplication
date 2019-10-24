package com.stu.syllabus.login.login;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.UtilModule;
import com.stu.syllabus.di.PerActivity;

import dagger.Component;

/**
 * yuan
 * 2019/10/23
 **/
@PerActivity
@Component(dependencies = AppComponent.class, modules = {LoginModule.class, com.stu.syllabus.login.LoginModule.class})
public interface LoginComponent {

    void inject(LoginActivity loginActivity);

}
