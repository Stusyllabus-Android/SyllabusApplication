package com.stu.syllabus.login.login;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;

/**
 * yuan
 * 2019/10/23
 **/
public interface LoginContract {

    interface presenter extends BasePresenter {
        void login(String account, String password);
    }

    interface view extends BaseView<presenter> {
        void toMainView();

        void showLoading(boolean isShow);

        void setLogin();

        void showFailMessage(String msg);
    }

}
