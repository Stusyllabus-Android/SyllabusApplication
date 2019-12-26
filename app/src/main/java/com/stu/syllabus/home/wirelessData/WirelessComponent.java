package com.stu.syllabus.home.wirelessData;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {WirelessModule.class})
public interface WirelessComponent {
    void inject(WirelessActivity wirelessActivity);
}
