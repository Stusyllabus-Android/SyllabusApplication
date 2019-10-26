package com.stu.syllabus.main;

import androidx.fragment.app.Fragment;

import com.stu.syllabus.base.BasePresenter;
import com.stu.syllabus.base.BaseView;
import com.stu.syllabus.login.login.LoginContract;

import java.util.List;

/**
 * yuan
 * 2019/10/26
 **/
public interface MainContract {

    interface  presenter extends BasePresenter {

    }
    interface view extends BaseView<LoginContract.presenter> {
        void initViewPager(List<Fragment> fragmentList);
        void initBottomNavigationView();
    }
}
