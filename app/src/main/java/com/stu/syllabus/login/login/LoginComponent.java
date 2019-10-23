package com.stu.syllabus.login.login;

import com.stu.syllabus.AppComponent;

import dagger.Component;

/**
 * yuan
 * 2019/10/23
 **/
@Component(dependencies = AppComponent.class, modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);

}
