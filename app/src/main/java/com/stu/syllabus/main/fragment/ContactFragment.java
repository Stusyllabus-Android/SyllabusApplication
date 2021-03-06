package com.stu.syllabus.main.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseFragment;

import butterknife.BindView;

/**
 * yuan
 * 2019/10/22
 **/
public class ContactFragment extends BaseFragment {
    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_contact;
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.contact);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }
}
