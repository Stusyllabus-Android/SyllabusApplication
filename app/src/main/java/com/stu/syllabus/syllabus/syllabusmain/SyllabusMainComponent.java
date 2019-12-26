package com.stu.syllabus.syllabus.syllabusmain;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.di.PerActivity;
import com.stu.syllabus.main.fragment.SyllabusFragment;

import dagger.Component;

/**
 * yuan
 * 2019/12/22
 **/
@PerActivity
@Component(dependencies = AppComponent.class, modules = SyllabusMainModule.class)
public interface SyllabusMainComponent {
    void inject(SyllabusFragment syllabusFragment);
}
