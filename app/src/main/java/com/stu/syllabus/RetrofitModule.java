package com.stu.syllabus;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.stu.syllabus.di.AuthRetrofit;
import com.stu.syllabus.di.BusinessRetrofit;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * yuan
 * 2019/10/23
 **/
@Module
public class RetrofitModule {

    OkHttpClient.Builder builder;

    public RetrofitModule() {
        super();
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);
    }

    @AuthRetrofit
    @Provides
    @Singleton
    public Retrofit provideAuthRetrofit() {
        return new Retrofit.Builder()
                .client(builder.build())
                .baseUrl("http://139.199.224.230:7002/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @BusinessRetrofit
    @Provides
    @Singleton
    public Retrofit provideBusinessRetrofit() {
        return new Retrofit.Builder()
                .client(builder.build())
                .baseUrl("http://139.199.224.230:7001/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
