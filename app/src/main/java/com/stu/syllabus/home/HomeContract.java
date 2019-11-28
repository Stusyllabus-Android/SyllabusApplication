package com.stu.syllabus.home;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;

public interface HomeContract {
    interface view extends BaseView {
        void setAdapterForListView();
    }
    interface presenter extends BasePresenter {

    }
}
