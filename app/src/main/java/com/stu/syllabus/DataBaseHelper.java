package com.stu.syllabus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * yuan
 * 2019/10/22
 **/
public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSkeyTable = "create table skey_table(skey varchar(64), refresh_key varchar(64))";
        String createUserInfoTable = "create table user_info(account varchar(64), password varchar(64))";
        db.execSQL(createSkeyTable);
        db.execSQL(createUserInfoTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
