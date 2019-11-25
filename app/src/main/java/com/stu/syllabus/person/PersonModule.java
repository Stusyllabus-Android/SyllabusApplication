package com.stu.syllabus.person;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.di.DataBase;
import com.stu.syllabus.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * yuan
 * 2019/11/25
 **/
@Module
public class PersonModule {

    PersonContract.view view;

    public PersonModule(PersonContract.view view) {
        super();
        this.view = view;
    }

    @Provides
    PersonContract.view provideView() {
        return view;
    }

    @Provides
    IPersonModel providePersonModel(@DataBase DataBaseHelper dataBaseHelper) {
        return new PersonModel(dataBaseHelper);
    }
}
