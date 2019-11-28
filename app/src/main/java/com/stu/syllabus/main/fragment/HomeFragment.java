package com.stu.syllabus.main.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.adapter.HomeListViewAdapter;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.bean.HomeItem;
import com.stu.syllabus.bean.HomeItemsItem;
import com.stu.syllabus.home.DaggerHomeComponent;
import com.stu.syllabus.home.HomeContract;
import com.stu.syllabus.home.HomeModule;
import com.stu.syllabus.home.HomePresenter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;

/**
 * @author wwshe
 * 2019/11/28
 **/
public class HomeFragment extends BaseFragment implements HomeContract.view {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.lv_home)
    ListView lv_home;

    @Inject
    HomePresenter homePresenter;

    private AppComponent appComponent;

    private HomeListViewAdapter homeLvAdapter;
    List<HomeItem> list_home; //home页面的list
    List<HomeItemsItem> item_item;  //上面list中每一项对应的功能list
    HomeItem homeItem;
    HomeItemsItem homeItemsItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = this.getComponent(AppComponent.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerHomeComponent.builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.home);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }

    @Override
    public void init() {
        homePresenter.init();
    }

    @Override
    public void setAdapterForListView() {
        list_home = new ArrayList<>();
        item_item = new ArrayList<>();
        //图书馆
        homeItemsItem = new HomeItemsItem();
        homeItemsItem.setItem("图书检索");
        item_item.add(homeItemsItem);
        homeItemsItem = new HomeItemsItem();
        homeItemsItem.setItem("预约研讨室");
        item_item.add(homeItemsItem);

        homeItem = new HomeItem();
        homeItem.setFunc("图书馆");
        homeItem.setFuncItem(item_item);
        list_home.add(homeItem);


        //预约
        item_item = new ArrayList<>();
        homeItemsItem = new HomeItemsItem();
        homeItemsItem.setItem("预约场地");
        item_item.add(homeItemsItem);
        homeItemsItem = new HomeItemsItem();
        homeItemsItem.setItem("预约教室");
        item_item.add(homeItemsItem);

        homeItem = new HomeItem();
        homeItem.setFunc("预约");
        homeItem.setFuncItem(item_item);
        list_home.add(homeItem);


        //流量监控
        item_item = new ArrayList<>();
        homeItemsItem = new HomeItemsItem();
        homeItemsItem.setItem("查看剩余流量");
        item_item.add(homeItemsItem);
        homeItem = new HomeItem();
        homeItem.setFunc("流量监控");
        homeItem.setFuncItem(item_item);
        list_home.add(homeItem);

        homeLvAdapter = new HomeListViewAdapter(getContext(), R.layout.item_home, list_home);
        lv_home.setAdapter(homeLvAdapter);
    }
}
