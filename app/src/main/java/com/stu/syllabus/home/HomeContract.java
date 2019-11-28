package com.stu.syllabus.home;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;

/**
 * @author wwshe
 * by 2019/11/28
 */
public interface HomeContract {
    interface view extends BaseView {
        void setAdapterForListView();
    }
    interface presenter extends BasePresenter {

    }
}
