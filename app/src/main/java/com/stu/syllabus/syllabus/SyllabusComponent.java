package com.stu.syllabus.syllabus;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.RetrofitModule;
import com.stu.syllabus.UtilModule;
import com.stu.syllabus.di.PerActivity;
import com.stu.syllabus.main.MainModule;
import com.stu.syllabus.main.fragment.ContactFragment;
import com.stu.syllabus.main.fragment.HomeFragment;
import com.stu.syllabus.main.fragment.InfoFragment;
import com.stu.syllabus.main.fragment.PersonFragment;
import com.stu.syllabus.main.fragment.SyllabusFragment;

import dagger.Component;

/**
 * yuan
 * 2019/11/12
 **/
@PerActivity
@Component(dependencies = AppComponent.class, modules = SyllabusModule.class)
public interface SyllabusComponent {
    void inject(SyllabusFragment syllabusFragment);
}
