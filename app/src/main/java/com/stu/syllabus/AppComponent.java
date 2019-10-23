package com.stu.syllabus;

import android.content.Context;

import com.stu.syllabus.di.AuthRetrofit;
import com.stu.syllabus.di.BusinessRetrofit;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * yuan
 * 2019/10/23
 **/
@Singleton
@Component(modules = {ApplicationModule.class, RetrofitModule.class})
public interface AppComponent {
    Context getApplicationContext();

    @AuthRetrofit
    Retrofit getAuthRetrofit();

    @BusinessRetrofit
    Retrofit getBussinessRetrofit();

}
