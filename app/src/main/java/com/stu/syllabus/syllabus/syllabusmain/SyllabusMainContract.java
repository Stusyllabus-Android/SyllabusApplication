package com.stu.syllabus.syllabus.syllabusmain;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;

/**
 * yuan
 * 2019/11/12
 **/
public interface SyllabusMainContract {
    interface view extends BaseView {
        void changeWallPaper();

        void showMsg(String message);
    }
    interface presenter extends BasePresenter {

    }
}
