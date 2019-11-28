package com.stu.syllabus.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.stu.syllabus.R;
import com.stu.syllabus.adapter.HomeListViewAdapter;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.bean.HomeItem;
import com.stu.syllabus.bean.HomeItemsItem;
import com.stu.syllabus.syllabus.SyllabusContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * yuan
 * 2019/10/22
 **/
public class HomeFragment extends BaseFragment implements SyllabusContract.view {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.lv_home)
    ListView lv_home;

    private HomeListViewAdapter homeLvAdapter;
    List<HomeItem> list_home; //home页面的list
    List<HomeItemsItem> item_item;  //上面list中每一项对应的功能list
    HomeItem homeItem;
    HomeItemsItem homeItemsItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getContentView() {
        list_home = new ArrayList<>();
        item_item = new ArrayList<>();

        //图书馆
        homeItemsItem.setItem("图书检索");
        item_item.add(homeItemsItem);
        homeItemsItem.setItem("预约研讨室");
        item_item.add(homeItemsItem);
        homeItem.setFunc("图书馆");
        homeItem.setFuncItem(item_item);
        list_home.add(homeItem);

        //预约
        homeItemsItem.setItem("预约场地");
        item_item.add(homeItemsItem);
        homeItemsItem.setItem("预约教室");
        item_item.add(homeItemsItem);
        homeItem.setFunc("预约");
        homeItem.setFuncItem(item_item);
        list_home.add(homeItem);

        //流量监控
        homeItemsItem.setItem("查看剩余流量");
        item_item.add(homeItemsItem);
        homeItemsItem.setItem("查看已用流量");
        item_item.add(homeItemsItem);
        homeItem.setFunc("流量监控");
        homeItem.setFuncItem(item_item);
        list_home.add(homeItem);

        setAdapterForListView();
        return R.layout.fragment_home;
    }


    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.home);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }

    public void setAdapterForListView() {
        homeLvAdapter = new HomeListViewAdapter(getContext(),list_home, getLayoutInflater());
        lv_home.setAdapter(homeLvAdapter);
    }
}
