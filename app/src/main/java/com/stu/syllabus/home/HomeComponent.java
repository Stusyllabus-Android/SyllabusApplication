package com.stu.syllabus.home;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.di.PerActivity;
import com.stu.syllabus.main.fragment.HomeFragment;

import dagger.Component;

/**
 *  @author :wwshe
 *  2019/11/28
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
}
