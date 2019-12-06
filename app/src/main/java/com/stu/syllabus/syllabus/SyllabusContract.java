package com.stu.syllabus.syllabus;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;
import com.stu.syllabus.bean.ShowLessonBean;

import java.util.List;

/**
 * yuan
 * 2019/11/12
 **/
public interface SyllabusContract {
    interface view extends BaseView {
        void showSyllabus(List<ShowLessonBean> lessonBeanList);
        void showMsg(String message);
    }
    interface presenter extends BasePresenter {

    }
}
