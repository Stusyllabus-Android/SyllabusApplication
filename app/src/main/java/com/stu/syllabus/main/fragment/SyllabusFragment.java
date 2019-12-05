package com.stu.syllabus.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.adapter.SyllabusListViewAdapter;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.syllabus .DaggerSyllabusComponent;
import com.stu.syllabus.syllabus.SyllabusContract;
import com.stu.syllabus.syllabus.SyllabusModule;
import com.stu.syllabus.syllabus.SyllabusPresenter;
import com.yalantis.phoenix.PullToRefreshView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * yuan
 * 2019/10/22
 **/
public class SyllabusFragment extends BaseFragment implements SyllabusContract.view{

    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @BindView(R.id.syllabusRefreshLayout)
    SwipeRefreshLayout syllabusRefreshLayout;
    @BindView(R.id.listView)
    ListView listView;

    private SyllabusListViewAdapter syllabusListViewAdapter;

    @Inject
    SyllabusPresenter syllabusPresenter;

    private AppComponent appComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = this.getComponent(AppComponent.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerSyllabusComponent.builder()
                .appComponent(appComponent)
                .syllabusModule(new SyllabusModule(this))
                .build()
                .inject(this);

        syllabusPresenter.init();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_syllabus;
    }

    @Override
    public void setAdapterForListView() {
        syllabusListViewAdapter = new SyllabusListViewAdapter(getContext(), R.layout.item_lesson, syllabusPresenter.getYiBanTimeTable().getTable());
        listView.setAdapter(syllabusListViewAdapter);
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.syllabus);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }

    @Override
    public void init() {
        syllabusRefreshLayout.setRefreshing(true);
        syllabusRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                syllabusPresenter.init();
            }
        });
    }

    @Override
    public void isRefresh(boolean flag) {
        syllabusRefreshLayout.setRefreshing(flag);
    }
}
