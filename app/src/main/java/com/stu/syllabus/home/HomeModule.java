package com.stu.syllabus.home;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {
    private final HomeContract.view view;

    public HomeModule(HomeContract.view view){
        super();
        this.view = view;
    }

    @Provides
    HomeContract.view provideView(){
        return view;
    }

}
