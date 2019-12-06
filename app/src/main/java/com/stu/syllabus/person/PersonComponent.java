package com.stu.syllabus.person;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.di.PerActivity;
import com.stu.syllabus.main.fragment.PersonFragment;

import dagger.Component;

/**
 * yuan
 * 2019/11/25
 **/
@PerActivity
@Component(dependencies = AppComponent.class, modules = PersonModule.class)
public interface PersonComponent {
    void inject(PersonFragment personFragment);
}
