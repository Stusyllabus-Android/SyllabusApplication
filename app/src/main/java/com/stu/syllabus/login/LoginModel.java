package com.stu.syllabus.login;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.stu.syllabus.bean.Authorize;
import com.stu.syllabus.bean.Login;
import com.stu.syllabus.bean.Oauth;
import com.stu.syllabus.bean.Skey;
import com.stu.syllabus.cookieInterceptor.AddCookieInterceptor;
import com.stu.syllabus.cookieInterceptor.ReceivedCookieInterceptor;
import com.stu.syllabus.retrofitApi.GetAuthorizeCodeApi;
import com.stu.syllabus.retrofitApi.GetOauthApi;
import com.stu.syllabus.retrofitApi.GetSkeyApi;
import com.stu.syllabus.retrofitApi.LoginApi;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * yuan
 * 2019/10/23
 **/
public class LoginModel implements ILoginModel {
    private String TAG = this.getClass().getSimpleName();

    GetOauthApi getOauthApi;
    LoginApi loginApi;
    GetAuthorizeCodeApi getAuthorizeCodeApi;
    GetSkeyApi getSkeyApi;

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    Retrofit businessRetrofit;
    Retrofit oauthRetrofit;

    OkHttpClient receiveOauthCookie;

    OkHttpClient addOauthCookie;

    OkHttpClient receiveBusinessCookie;

    OkHttpClient addBusinessCookie;

    OkHttpClient addTwoCookies;

    public LoginModel(SQLiteOpenHelper sqLiteOpenHelper) {
        super();

        this.dbHelper = sqLiteOpenHelper;

        receiveOauthCookie = new OkHttpClient.Builder()
                .addInterceptor(new ReceivedCookieInterceptor("OauthCookie"))
                .build();

        addOauthCookie = new OkHttpClient.Builder()
                .addInterceptor(new AddCookieInterceptor("OauthCookie"))
                .build();

        receiveBusinessCookie = new OkHttpClient.Builder()
                .addInterceptor(new ReceivedCookieInterceptor("BusinessCookie"))
                .build();

        addBusinessCookie = new OkHttpClient.Builder()
                .addInterceptor(new AddCookieInterceptor("BusinessCookie"))
                .build();

        addTwoCookies = new OkHttpClient.Builder()
                .addInterceptor(new AddCookieInterceptor("BusinessCookie"))
                .addInterceptor(new AddCookieInterceptor("OauthCookie"))
                .build();

        getOauthApi = new Retrofit.Builder()
                .baseUrl("http://139.199.224.230:7002/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(receiveBusinessCookie)
                .build()
                .create(GetOauthApi.class);

        loginApi = new Retrofit.Builder()
            .baseUrl("http://139.199.224.230:7001/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(receiveOauthCookie)
            .build()
            .create(LoginApi.class);

        getAuthorizeCodeApi = new Retrofit.Builder()
            .baseUrl("http://139.199.224.230:7001/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(addOauthCookie)
            .build()
            .create(GetAuthorizeCodeApi.class);

        getSkeyApi = new Retrofit.Builder()
            .baseUrl("http://139.199.224.230:7002/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(addTwoCookies)
            .build()
            .create(GetSkeyApi.class);
    }

    @Override
    public Observable<Skey> getSkeyFromDisk() {
        // TODO: 2019/10/24 如果为空则去登录界面，否则进入主界面
        return null;
    }

    @Override
    public Observable<Skey> getSkeyFromNet(String code, String state, String from) {
        return getSkeyApi.getSkey(code, state, from)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Oauth> getOauthFromNet() {
        return getOauthApi.getOauth()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Login> getLoginFromNet(String account, String password) {
        return loginApi.login(account, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Authorize> getAuthorizeCodeFromNet(String response_type, String client_id, String state, String scope, String from) {
        return getAuthorizeCodeApi.getAuthorizeCode(response_type, client_id, state, scope, from)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
