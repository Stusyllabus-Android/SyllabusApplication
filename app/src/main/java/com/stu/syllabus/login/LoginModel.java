package com.stu.syllabus.login;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    public void saveSkeyToDisk(String skey, String refresh_key) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("skey", skey);
        values.put("refresh_key", refresh_key);
        sqLiteDatabase.insert("skey_table", null, values);
        sqLiteDatabase.close();
    }

    @Override
    public String getSkeyFromDisk() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        String sql = "select * from skey_table";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        String skey = null;
        String refresh_key = null;
        while (cursor.moveToNext()) {
            skey = cursor.getString(cursor.getColumnIndex("skey"));
            refresh_key = cursor.getString(cursor.getColumnIndex("refresh_key"));
            Log.d(TAG, "getSkeyFromDisk: " + skey + " " + refresh_key);
        }
        sqLiteDatabase.close();
        return skey;
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