package com.stu.syllabus.syllabus;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.bean.UserInfo;
import com.stu.syllabus.bean.YiBanTimeTable;
import com.stu.syllabus.bean.YiBanToken;
import com.stu.syllabus.di.YiBanRetrofit;
import com.stu.syllabus.retrofitApi.YiBanApi;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * yuan
 * 2019/11/12
 **/
public class SyllabusModel implements ISyllabusModel {
    private final String TAG = this.getClass().getSimpleName();

    // TODO: 2019/11/12 主要是为了拿到用户的账号和密码 
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Retrofit retrofit;
    YiBanApi yiBanApi;

    public SyllabusModel(DataBaseHelper dataBase, Retrofit retrofit) {
        super();
        this.dataBaseHelper = dataBase;
        this.retrofit = retrofit;
        yiBanApi = retrofit.create(YiBanApi.class);
    }

    @Override
    public Observable<UserInfo> getUserInfoFromDisk() {
        return Observable.create(new ObservableOnSubscribe<UserInfo>() {
            @Override
            public void subscribe(ObservableEmitter<UserInfo> emitter) throws Exception {
                String account = null;
                String password = null;
                sqLiteDatabase = dataBaseHelper.getReadableDatabase();
                String sql = "select * from user_info";
                Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    account = cursor.getString(cursor.getColumnIndex("account"));
                    password = cursor.getString(cursor.getColumnIndex("password"));
                }
                sqLiteDatabase.close();
                UserInfo userInfo = new UserInfo(account, password);
                // TODO: 2019/11/15 好像不能发送对象 
                emitter.onNext(userInfo);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> getRequestToken() {
        return yiBanApi.getRequestToken()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> login(String email, String password, String requestToken) {
        return yiBanApi.login(email, password, requestToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<YiBanToken> getToken() {
        return yiBanApi.getToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<YiBanTimeTable> getTimeTable(long vid, long timestamp, String app, String nonce, String token) {
        return yiBanApi.getTimeTable(vid, timestamp, app, nonce, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
