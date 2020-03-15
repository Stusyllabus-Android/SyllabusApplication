package com.stu.syllabus.person.personal;

import android.content.Intent;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;

/**
 * yuan
 * 2019/12/23
 **/
public interface PersonalContract {
    interface view extends BaseView {
        void loadInfo(String avatar, String account, String nickname, String signature);
    }
    interface presenter extends BasePresenter {
        void updateUserInfo(String avatar, String nickname, String signature);
    }
}
