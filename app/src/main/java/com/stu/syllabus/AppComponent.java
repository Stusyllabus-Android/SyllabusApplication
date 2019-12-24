package com.stu.syllabus;

import android.content.Context;

import com.stu.syllabus.di.AuthRetrofit;
import com.stu.syllabus.di.BusinessRetrofit;
import com.stu.syllabus.di.DataBase;
import com.stu.syllabus.di.OASearchRetrofit;
import com.stu.syllabus.di.YiBanRetrofit;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * yuan
 * 2019/10/23
 **/
@Singleton
@Component(modules = {ApplicationModule.class, UtilModule.class, RetrofitModule.class})
public interface AppComponent {
    Context getApplicationContext();

    @DataBase
    DataBaseHelper getDataBaseHelper();

    @AuthRetrofit
    Retrofit getAuthRetrofit();

    @BusinessRetrofit
    Retrofit getBusinessRetrofit();

    @YiBanRetrofit
    Retrofit getYiBanRetrofit();

    @OASearchRetrofit
    Retrofit getOASearchRetrofit();

    void inject(App app);

}
