package com.stu.syllabus.main.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.adapter.SyllabusListViewAdapter;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.syllabus.DaggerSyllabusComponent;
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
    PullToRefreshView pullToRefreshView;
    @BindView(R.id.listView)
    ListView listView;

    private SyllabusListViewAdapter syllabusListViewAdapter;

    @Inject
    SyllabusPresenter syllabusPresenter;

    private AppComponent appComponent;
    private long REFRESH_DELAY = 2000;

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
        pullToRefreshView.setRefreshing(true);
        syllabusPresenter.init();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_syllabus;
    }

    @Override
    public void setAdapterForListView() {
        syllabusListViewAdapter = new SyllabusListViewAdapter(getContext(), R.layout.lesson_item, syllabusPresenter.getYiBanTimeTable().getTable());
        listView.setAdapter(syllabusListViewAdapter);
        pullToRefreshView.setRefreshing(false);
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.syllabus);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }

    @Override
    public void init() {
        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        syllabusPresenter.init();
                        pullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });
    }
}
