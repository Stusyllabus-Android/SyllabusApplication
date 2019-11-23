package com.stu.syllabus.syllabus;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.di.DataBase;
import com.stu.syllabus.di.YiBanRetrofit;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * yuan
 * 2019/11/12
 **/
@Module
public class SyllabusModule {

    SyllabusContract.view view;

    public SyllabusModule(SyllabusContract.view view) {
        super();
        this.view = view;
    }

    @Provides
    SyllabusContract.view provideView() {
        return view;
    }

    @Provides
    ISyllabusModel provideSyllabusModel(@DataBase DataBaseHelper dataBase, @YiBanRetrofit Retrofit retrofit) {
        return new SyllabusModel(dataBase, retrofit);
    }
}
