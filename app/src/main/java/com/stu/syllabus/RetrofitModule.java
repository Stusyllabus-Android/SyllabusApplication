package com.stu.syllabus;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.CookieCache;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.stu.syllabus.di.AuthRetrofit;
import com.stu.syllabus.di.BusinessRetrofit;
import com.stu.syllabus.di.OASearchRetrofit;
import com.stu.syllabus.di.YiBanRetrofit;
import com.stu.syllabus.di.WirelessRetrofit;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
    private final String WIRELESS_BASEURL = "http://1.1.1.2/ac_portal/";

    private final String OASEARCH_BASEURL ="http://oauth.candycute.cn:7001/";
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
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Provides
    @WirelessRetrofit
    Retrofit provideWirelessRetrofit(){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(WIRELESS_BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @OASearchRetrofit
    Retrofit provideAuthSearchRetrofit() {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(OASEARCH_BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
