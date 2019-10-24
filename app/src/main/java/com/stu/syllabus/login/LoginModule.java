package com.stu.syllabus.login;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.di.AuthRetrofit;
import com.stu.syllabus.di.BusinessRetrofit;
import com.stu.syllabus.di.DataBase;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * yuan
 * 2019/10/24
 **/
@Module
public class LoginModule {
    @Provides
    ILoginModel provideLoginModel(@DataBase DataBaseHelper database, @AuthRetrofit Retrofit authRetrofit, @BusinessRetrofit Retrofit businessRetrofit) {
        return new LoginModel(database, authRetrofit, businessRetrofit);
    }
}
