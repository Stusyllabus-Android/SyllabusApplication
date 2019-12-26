package com.stu.syllabus.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.stu.syllabus.main.fragment.HomeBookFragment;
import com.stu.syllabus.main.fragment.HomeLibraryFragment;
import com.stu.syllabus.main.fragment.HomeWirelessFragment;

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

    public HomeViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        Log.d("HomeViewPagerAdapter---", "enter---");
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

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }
}
