package com.stu.syllabus.person.setting;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;

/**
 * yuan
 * 2019/11/24
 **/
public interface SettingContract {
    interface view extends BaseView {
        void showShareDialog();
        void toLoginActivity();
    }
    interface presenter extends BasePresenter {
        void recommendTo();
        void logout();
    }
}
