package com.stu.syllabus.main;

import androidx.fragment.app.Fragment;

import com.stu.syllabus.main.fragment.ContactFragment;
import com.stu.syllabus.main.fragment.HomeFragment;
import com.stu.syllabus.main.fragment.InfoFragment;
import com.stu.syllabus.main.fragment.PersonFragment;
import com.stu.syllabus.main.fragment.SyllabusFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * yuan
 * 2019/10/26
 **/
public class MainPresenter implements MainContract.presenter{

    MainContract.view view;
    MainModel mainModel;

    private List<Fragment> fragmentList;

    @Inject
    public MainPresenter(MainContract.view view, MainModel mainModel) {
        super();
        this.view = view;
        this.mainModel = mainModel;
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new InfoFragment());
        fragmentList.add(new SyllabusFragment());
        fragmentList.add(new ContactFragment());
        fragmentList.add(new PersonFragment());
    }

    @Override
    public void init() {
        view.initViewPager(fragmentList);
        view.initBottomNavigationView();
    }
}
