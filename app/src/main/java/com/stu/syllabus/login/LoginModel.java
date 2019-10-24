package com.stu.syllabus.login;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.bean.Authorize;
import com.stu.syllabus.bean.Login;
import com.stu.syllabus.bean.Oauth;
import com.stu.syllabus.bean.Skey;
import com.stu.syllabus.retrofitApi.GetAuthorizeCodeApi;
import com.stu.syllabus.retrofitApi.GetOauthApi;
import com.stu.syllabus.retrofitApi.GetSkeyApi;
import com.stu.syllabus.retrofitApi.LoginApi;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

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

    public LoginModel(SQLiteOpenHelper sqLiteOpenHelper, Retrofit oauthRetrofit, Retrofit businessRetrofit) {
        super();
        this.dbHelper = sqLiteOpenHelper;
        getOauthApi = businessRetrofit.create(GetOauthApi.class);
        loginApi = oauthRetrofit.create(LoginApi.class);
        getAuthorizeCodeApi = oauthRetrofit.create(GetAuthorizeCodeApi.class);
        getSkeyApi = businessRetrofit.create(GetSkeyApi.class);
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
