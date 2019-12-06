package com.stu.syllabus.login;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.stu.syllabus.bean.Authorize;
import com.stu.syllabus.bean.Login;
import com.stu.syllabus.bean.Oauth;
import com.stu.syllabus.bean.Skey;
import com.stu.syllabus.bean.UserInfo;
import com.stu.syllabus.cookieInterceptor.AddCookieInterceptor;
import com.stu.syllabus.cookieInterceptor.ReceivedCookieInterceptor;
import com.stu.syllabus.retrofitApi.GetAuthorizeCodeApi;
import com.stu.syllabus.retrofitApi.GetOauthApi;
import com.stu.syllabus.retrofitApi.GetSkeyApi;
import com.stu.syllabus.retrofitApi.LoginApi;
import com.stu.syllabus.retrofitApi.OperateUserInfoApi;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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
    OperateUserInfoApi operateUserInfoApi;

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

        operateUserInfoApi = new Retrofit.Builder()
                .baseUrl("http://139.199.224.230:7002/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OperateUserInfoApi.class);
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
    public void saveUserBaseInfoToDisk(String account, String password) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("account", account);
        values.put("password", password);
        sqLiteDatabase.insert("user_base_info", null, values);
        sqLiteDatabase.close();
    }

    @Override
    public void saveUserInfoToDisk(String id, String avatar, String nickname, String signature, String semester) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("avatar", avatar);
        values.put("nickname", nickname);
        values.put("signature", signature);
        values.put("semester", semester);
        sqLiteDatabase.insert("user_info", null, values);
        sqLiteDatabase.close();
    }

    @Override
    public Observable<String> getSkeyFromDisk() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String skey = null;
                sqLiteDatabase = dbHelper.getReadableDatabase();
                String sql = "select * from skey_table";
                Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    skey = cursor.getString(cursor.getColumnIndex("skey"));
                }
                sqLiteDatabase.close();
                if (skey == null) {
                    skey = "Non-existent";
                }
                emitter.onNext(skey);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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

    @Override
    public Observable<UserInfo> getUserInfoFromNet(String skey, String url, String method, String from) {
        return operateUserInfoApi.getUserInfo(skey, url, method, from)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
