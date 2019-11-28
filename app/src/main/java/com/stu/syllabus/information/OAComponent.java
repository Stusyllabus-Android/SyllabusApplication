package com.stu.syllabus.information;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.di.PerActivity;
import com.stu.syllabus.main.fragment.InfoFragment;

import dagger.Component;

/**
 * yuan
 * 2019/11/28
 **/
@PerActivity
@Component(dependencies = AppComponent.class, modules = OAModule.class)
public interface OAComponent {
    void inject(InfoFragment infoFragment);
}
