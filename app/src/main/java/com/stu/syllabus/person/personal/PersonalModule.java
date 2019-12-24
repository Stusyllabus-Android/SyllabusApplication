package com.stu.syllabus.person.personal;

import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.di.DataBase;
import com.stu.syllabus.di.PerActivity;

import javax.annotation.PreDestroy;

import dagger.Module;
import dagger.Provides;

/**
 * yuan
 * 2019/12/23
 **/
@Module
public class PersonalModule {

    PersonalContract.view view;

    public PersonalModule(PersonalContract.view view) {
        super();
        this.view = view;
    }

    @Provides
    PersonalContract.view provideView() {
        return view;
    }

    @Provides
    IPersonalModel providePersonalModel(@DataBase DataBaseHelper dataBaseHelper) {
        return new PersonalModel(dataBaseHelper);
    }
}
