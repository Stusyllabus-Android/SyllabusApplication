package com.stu.syllabus.person.personal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.bean.PostUserInfoResult;
import com.stu.syllabus.bean.ShowInfoBean;
import com.stu.syllabus.retrofitApi.OperateUserInfoApi;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * yuan
 * 2019/12/23
 **/
public class PersonalModel implements IPersonalModel{

    private String TAG = this.getClass().getSimpleName();

    SQLiteOpenHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;

    private static String skey;

    static OperateUserInfoApi operateUserInfoApi;

    public PersonalModel(DataBaseHelper dataBaseHelper) {
        super();
        this.dataBaseHelper = dataBaseHelper;

        operateUserInfoApi = new Retrofit.Builder()
                .baseUrl("http://139.199.224.230:7002/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OperateUserInfoApi.class);

        getSkeyFromDisk();
    }

    private void getSkeyFromDisk() {
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        String sql = "select * from skey_table";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            skey = cursor.getString(cursor.getColumnIndex("skey"));
        }
        sqLiteDatabase.close();
        Log.d(TAG, "getSkeyFromDisk: " + skey);
    }

    @Override
    public void saveUserInfoToDisk(String avatar, String nickname, String signature) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("avatar", avatar);
        values.put("nickname", nickname);
        values.put("signature", signature);
        sqLiteDatabase.insert("user_info", null, values);
        sqLiteDatabase.close();
    }

    @Override
    public Observable<ShowInfoBean> getUserInfoFromDisk() {
        return Observable.create(new ObservableOnSubscribe<ShowInfoBean>() {
            @Override
            public void subscribe(ObservableEmitter<ShowInfoBean> emitter) throws Exception {
                String id = null;
                String avatar = null;
                String nickname = null;
                String signature = null;
                String semester = null;
                sqLiteDatabase = dataBaseHelper.getReadableDatabase();
                String sql = "select * from user_info";
                Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    id = cursor.getString(cursor.getColumnIndex("id"));
                    avatar = cursor.getString(cursor.getColumnIndex("avatar"));
                    nickname = cursor.getString(cursor.getColumnIndex("nickname"));
                    signature = cursor.getString(cursor.getColumnIndex("signature"));
                    semester = cursor.getString(cursor.getColumnIndex("semester"));
                }
                sqLiteDatabase.close();
                if (semester == null) {
                    semester = "Non-existent";
                }
                ShowInfoBean infoBean = new ShowInfoBean(id, avatar, nickname, signature, semester);
                emitter.onNext(infoBean);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<PostUserInfoResult> updateAvatar(String avatar) {
        return operateUserInfoApi.updateAvatar(skey, "/user/upload/avatar", "post", "android", avatar)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<PostUserInfoResult> updateNickname(String nickname) {
        return operateUserInfoApi.updateNickname(skey, "/user/modify/nickname", "post", "android", nickname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<PostUserInfoResult> updateSignature(String signature) {
        return operateUserInfoApi.updateSignature(skey, "/user/modify/signature", "post", "android", signature)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
