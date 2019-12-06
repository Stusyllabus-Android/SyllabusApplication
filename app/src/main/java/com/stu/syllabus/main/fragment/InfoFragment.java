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

import com.stu.syllabus.App;
import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.adapter.OAListAdapter;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.bean.OAArticle;
import com.stu.syllabus.information.DaggerOAComponent;
import com.stu.syllabus.information.OAContract;
import com.stu.syllabus.information.OAModule;
import com.stu.syllabus.information.OAPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * yuan
 * 2019/10/22
 **/
public class InfoFragment extends BaseFragment implements OAContract.view {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.oaRefreshLayout)
    SwipeRefreshLayout oaRefreshLayout;
    @BindView(R.id.listView)
    ListView listView;

    private AppComponent appComponent;

    @Inject
    OAPresenter oaPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = this.getComponent(AppComponent.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerOAComponent.builder()
                .appComponent(appComponent)
                .oAModule(new OAModule(this))
                .build()
                .inject(this);

        oaPresenter.init();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_info;
    }

    @Override
    public void init() {
        super.init();
        oaRefreshLayout.setRefreshing(true);
        oaRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                oaPresenter.init();
            }
        });
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.info);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }

    @Override
    public void showOA(List<OAArticle> oaArticleList) {
        listView.setAdapter(new OAListAdapter(getContext(), R.layout.item_oa, oaArticleList));
    }

    @Override
    public void isRefresh(boolean flag) {
        oaRefreshLayout.setRefreshing(flag);
    }
}
