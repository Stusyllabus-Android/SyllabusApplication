package com.stu.syllabus.login.splash;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;

/**
 * yuan
 * 2019/10/23
 **/
public interface SplashContract {

    interface presenter extends BasePresenter {

    }

    interface view extends BaseView<presenter> {
        void toLoginView();

        void toMainView();
    }
}
