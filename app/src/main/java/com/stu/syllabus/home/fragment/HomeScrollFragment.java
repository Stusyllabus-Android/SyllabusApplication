package com.stu.syllabus.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.stu.syllabus.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScrollFragment extends Fragment {
    @BindView(R.id.scrollView)
    NestedScrollView mScrollView;

    public static HomeScrollFragment newInstance() {
        return new HomeScrollFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_scroll, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView);
    }
}
