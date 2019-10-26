package com.stu.syllabus;

import android.content.Context;

import com.stu.syllabus.di.DataBase;

import dagger.Module;
import dagger.Provides;

/**
 * yuan
 * 2019/10/23
 **/
@Module
public class UtilModule {
    final Context context;

    public UtilModule(Context context) {
        this.context = context;
    }

    @Provides
    @DataBase
    DataBaseHelper provideDataBaseHelper(Context context) {
        return new DataBaseHelper(context, "syllabus", null, 1);
    }
}
