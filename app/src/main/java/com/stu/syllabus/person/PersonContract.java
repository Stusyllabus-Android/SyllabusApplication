package com.stu.syllabus.person;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;

/**
 * yuan
 * 2019/11/25
 **/
public interface PersonContract {
    interface view extends BaseView {
        void init(String id, String avatar, String nickname, String signature);
    }
    interface presenter extends BasePresenter {

    }
}
