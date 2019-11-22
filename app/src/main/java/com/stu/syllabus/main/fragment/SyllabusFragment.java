package com.stu.syllabus.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.stu.syllabus.App;
import com.stu.syllabus.AppComponent;
import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.syllabus.DaggerSyllabusComponent;
import com.stu.syllabus.syllabus.SyllabusModule;

import butterknife.BindView;
import retrofit2.Retrofit;

/**
 * yuan
 * 2019/10/22
 **/
public class SyllabusFragment extends BaseFragment {

    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO: 2019/11/12 缺appcomponent
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_syllabus;
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.syllabus);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }
}
