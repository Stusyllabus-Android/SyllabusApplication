package com.stu.syllabus.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.stu.syllabus.main.fragment.HomeBookFragment;
import com.stu.syllabus.main.fragment.HomeLibraryFragment;
import com.stu.syllabus.main.fragment.HomeWirelessFragment;

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {


    public HomeViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position % 3) {
            case 0:
                return HomeLibraryFragment.newInstance(position);
            case 1:
                return HomeWirelessFragment.newInstance(position);
            case 2:
                return HomeBookFragment.newInstance(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position % 3) {
            case 0:
                return "图书馆";
            case 1:
                return "流量监控";
            case 2:
                return "预约";
        }
        return "";
    }
}
