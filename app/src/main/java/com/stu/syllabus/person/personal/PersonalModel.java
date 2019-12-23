package com.stu.syllabus.person.personal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.bean.ShowInfoBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * yuan
 * 2019/12/23
 **/
public class PersonalModel implements IPersonalModel{

    SQLiteOpenHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;

    public PersonalModel(DataBaseHelper dataBaseHelper) {
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
    public Observable<String> updateAvatar() {
        return null;
    }

    @Override
    public Observable<String> updateNickname() {
        return null;
    }

    @Override
    public Observable<String> updateSignature() {
        return null;
    }
}
