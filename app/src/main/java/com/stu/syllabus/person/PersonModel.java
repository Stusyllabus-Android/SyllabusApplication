package com.stu.syllabus.person;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.bean.ShowInfoBean;
import com.stu.syllabus.bean.UserInfo;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * yuan
 * 2019/11/25
 **/
public class PersonModel implements IPersonModel {

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;

    public PersonModel(DataBaseHelper dataBaseHelper) {
        super();
        this.dataBaseHelper = dataBaseHelper;
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
                ShowInfoBean infoBean = new ShowInfoBean(id, avatar, nickname, signature, semester);
                emitter.onNext(infoBean);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void updateCurrentSemester(String currentSemester) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("semester", currentSemester);
        sqLiteDatabase.update("user_info", values, null, null);
        sqLiteDatabase.close();
    }
}
