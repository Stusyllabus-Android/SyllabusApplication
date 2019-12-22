package com.stu.syllabus.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.stu.syllabus.syllabus.syllabus.SyllabusFragment;

/**
 * yuan
 * 2019/12/22
 **/
public class SyllabusViewPagerAdapter extends FragmentPagerAdapter {

    public SyllabusViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return SyllabusFragment.newInstance(position );
    }

    @Override
    public int getCount() {
        return 18;
    }
}
