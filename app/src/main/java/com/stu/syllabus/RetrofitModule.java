package com.stu.syllabus;

import android.app.Application;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.CookieCache;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.stu.syllabus.di.AuthRetrofit;
import com.stu.syllabus.di.BusinessRetrofit;
import com.stu.syllabus.di.YiBanRetrofit;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * yuan
 * 2019/10/26
 **/
@Module
public class RetrofitModule {

    private final int TIMEOUT = 5;
    private final String OAUTH_BASEURL = "http://139.199.224.230:7001/";
    private final String BUSINESS_BASEURL = "http://139.199.224.230:7002/";
    private final String YIBAN_BASEURL = "https://yiban.stu.edu.cn/";

    CookieCache cookieCache;
    CookieJar cookieJar;

    OkHttpClient okHttpClient;

    public RetrofitModule() {
        super();
        // TODO: 2019/11/12 统一在这里处理cookie

        cookieCache = new SetCookieCache();
        cookieJar = new PersistentCookieJar(cookieCache, new SharedPrefsCookiePersistor(App.getContext()));

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .callTimeout(TIMEOUT, TimeUnit.SECONDS)
                .cookieJar(cookieJar)
                .build();
    }

    @Provides
    @AuthRetrofit
    Retrofit provideAuthRetrofit() {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(OAUTH_BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @BusinessRetrofit
    Retrofit provideBusinessRetrofit() {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BUSINESS_BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @YiBanRetrofit
    Retrofit provideYiBanRetrofit() {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(YIBAN_BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
