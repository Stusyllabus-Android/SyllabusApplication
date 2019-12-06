package com.stu.syllabus.person.setting;

import android.database.sqlite.SQLiteDatabase;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.di.DataBase;

import java.util.Set;

import dagger.Module;
import dagger.Provides;

/**
 * yuan
 * 2019/11/24
 **/
@Module
public class SettingModule {
    private SettingContract.view view;

    public SettingModule(SettingContract.view view) {
        super();
        this.view = view;
    }

    @Provides
    SettingContract.view provideView() {
        return view;
    }

    @Provides
    ISettingModel provideSettingModel(@DataBase DataBaseHelper dataBaseHelper) {
        return new SettingModel(dataBaseHelper);
    }
}
