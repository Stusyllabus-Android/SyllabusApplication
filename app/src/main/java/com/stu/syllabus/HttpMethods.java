package com.stu.syllabus;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.stu.syllabus.bean.Login;
import com.stu.syllabus.retrofitApi.LoginApi;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpMethods {
    private static final HttpMethods ourInstance = new HttpMethods();

    private static final String BASE_URL = "https://class.stuapps.com/";

    private LoginApi loginApi;

    public static HttpMethods getInstance() {
        return ourInstance;
    }

    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        loginApi = retrofit.create(LoginApi.class);
    }

    public void login(Observer<Login> observer, String account, String password) {
        loginApi.login(account, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
