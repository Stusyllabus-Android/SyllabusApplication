package com.stu.syllabus.main;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.di.PerActivity;
import dagger.Component;

/**
 * yuan
 * 2019/10/26
 **/
@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
