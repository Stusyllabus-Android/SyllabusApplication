package com.stu.syllabus.syllabus.syllabus;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.di.PerActivity;

import dagger.Component;

/**
 * yuan
 * 2019/12/22
 **/
@PerActivity
@Component(dependencies = AppComponent.class, modules = SyllabusModule.class)
public interface SyllabusComponent {
    void inject(SyllabusFragment syllabusFragment);
}
