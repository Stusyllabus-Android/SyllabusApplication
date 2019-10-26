package com.stu.syllabus.main;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.di.AuthRetrofit;
import com.stu.syllabus.di.BusinessRetrofit;
import com.stu.syllabus.di.DataBase;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * yuan
 * 2019/10/26
 **/
@Module
public class MainModule {
    private final MainContract.view view;

    public MainModule(MainContract.view view) {
        super();
        this.view = view;

    }

    @Provides
    MainContract.view provideView() {
        return view;
    }
    @Provides
    MainModel provideMainModel(@DataBase DataBaseHelper dataBaseHelper, @AuthRetrofit Retrofit authRetrofit, @BusinessRetrofit Retrofit businessRetrofit) {
        return new MainModel(dataBaseHelper, authRetrofit, businessRetrofit);
    }
}
