package com.stu.syllabus.home;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.di.PerActivity;
import com.stu.syllabus.main.fragment.HomeFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
}
