package com.stu.syllabus.main;

import androidx.fragment.app.Fragment;

import com.stu.syllabus.R;
import com.stu.syllabus.main.fragment.ContactFragment;
import com.stu.syllabus.main.fragment.HomeFragment;
import com.stu.syllabus.main.fragment.InfoFragment;
import com.stu.syllabus.main.fragment.PersonFragment;
import com.stu.syllabus.main.fragment.SyllabusFragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * yuan
 * 2019/10/26
 **/
public class MainPresenter implements MainContract.presenter{

    MainContract.view view;

    List<Fragment> fragmentList;
    @Inject
    public MainPresenter(MainContract.view view) {
        super();
        this.view = view;
        fragmentList = new LinkedList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new InfoFragment());
        fragmentList.add(new SyllabusFragment());
        fragmentList.add(new ContactFragment());
        fragmentList.add(new PersonFragment());
    }

    @Override
    public void init() {
        view.onTabItemSelected(fragmentList, R.id.home);
        view.initBottomNavigationView(fragmentList);
    }
}
