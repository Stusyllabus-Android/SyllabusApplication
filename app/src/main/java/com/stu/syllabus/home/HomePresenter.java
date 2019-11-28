package com.stu.syllabus.home;

import android.util.Log;

import javax.inject.Inject;

public class HomePresenter implements HomeContract.presenter {
    private final String TAG = this.getClass().getSimpleName();

    HomeContract.view view;


    @Inject
    public HomePresenter(HomeContract.view view){
        this.view = view;
    }
    @Override
    public void init() {
        view.setAdapterForListView();
    }
}
