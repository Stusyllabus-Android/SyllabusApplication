package com.stu.syllabus.syllabus;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;
import com.stu.syllabus.bean.UserInfo;

/**
 * yuan
 * 2019/11/12
 **/
public interface SyllabusContract {
    interface view extends BaseView {
        void setAdapterForListView();
    }
    interface presenter extends BasePresenter {

    }
}
