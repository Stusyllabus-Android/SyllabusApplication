package com.stu.syllabus.person.personal;

import com.stu.syllabus.App;
import com.stu.syllabus.AppComponent;
import com.stu.syllabus.di.PerActivity;
import com.stu.syllabus.person.PersonModule;

import dagger.Component;

/**
 * yuan
 * 2019/12/23
 **/
@PerActivity
@Component(dependencies = AppComponent.class, modules = PersonalModule.class)
public interface PersonalComponent {
    void inject(PersonalActivity personalActivity);
}
