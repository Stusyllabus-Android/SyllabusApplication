package com.stu.syllabus.syllabus.syllabus;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;
import com.stu.syllabus.bean.ShowLessonBean;

import java.util.List;

/**
 * yuan
 * 2019/12/22
 **/
public interface SyllabusContract {
    interface view extends BaseView {
        void showMsg(String msg);
        void showSyllabus(List<ShowLessonBean> lessonBeans);
    }
    interface presenter extends BasePresenter {

    }
}
