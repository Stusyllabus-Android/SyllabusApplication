package com.stu.syllabus.login;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.di.DataBase;
import dagger.Module;
import dagger.Provides;

/**
 * yuan
 * 2019/10/24
 **/
@Module
public class LoginModule {
    @Provides
    ILoginModel provideLoginModel(@DataBase DataBaseHelper database) {
        return new LoginModel(database);
    }
}