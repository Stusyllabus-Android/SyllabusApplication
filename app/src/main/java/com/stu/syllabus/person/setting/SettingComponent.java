package com.stu.syllabus.person.setting;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.di.PerActivity;

import dagger.Component;

/**
 * yuan
 * 2019/11/24
 **/
@PerActivity
@Component(dependencies = AppComponent.class, modules = SettingModule.class)
public interface SettingComponent {
    void inject(SettingActivity settingActivity);
}
