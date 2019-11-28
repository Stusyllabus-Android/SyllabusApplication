package com.stu.syllabus.home;

import javax.inject.Inject;

/**
 * @author wwshe
 * by 2019/11/28
 */
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
