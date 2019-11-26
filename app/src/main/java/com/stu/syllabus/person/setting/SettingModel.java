package com.stu.syllabus.person.setting;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * yuan
 * 2019/11/24
 **/
public class SettingModel implements ISettingModel {

    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    public SettingModel(SQLiteOpenHelper sqLiteOpenHelper) {
        super();
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    @Override
    public void logout() {
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        sqLiteDatabase.delete("skey_table", null, new String[]{});
        sqLiteDatabase.delete("user_base_info", null, new String[]{});
        sqLiteDatabase.delete("user_info", null, new String[]{});
        sqLiteDatabase.close();
    }
}
