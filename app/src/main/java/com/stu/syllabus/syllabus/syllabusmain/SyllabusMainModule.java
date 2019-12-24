package com.stu.syllabus.syllabus.syllabusmain;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.di.DataBase;

import dagger.Module;
import dagger.Provides;

/**
 * yuan
 * 2019/12/22
 **/
@Module
public class SyllabusMainModule {
    SyllabusMainContract.view view;

    public SyllabusMainModule(SyllabusMainContract.view view) {
        super();
        this.view = view;
    }

    @Provides
    SyllabusMainContract.view provideView() {
        return view;
    }

    @Provides
    ISyllabusMainModel provideSyllabusModel(@DataBase DataBaseHelper dataBase) {
        return new SyllabusMainModel(dataBase);
    }
}
