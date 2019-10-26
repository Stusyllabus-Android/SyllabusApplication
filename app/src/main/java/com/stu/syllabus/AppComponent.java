package com.stu.syllabus;

import android.content.Context;

import com.stu.syllabus.di.DataBase;

import javax.inject.Singleton;

import dagger.Component;

/**
 * yuan
 * 2019/10/23
 **/
@Singleton
@Component(modules = {ApplicationModule.class, UtilModule.class})
public interface AppComponent {
    Context getApplicationContext();

    @DataBase
    DataBaseHelper getDataBaseHelper();

}
