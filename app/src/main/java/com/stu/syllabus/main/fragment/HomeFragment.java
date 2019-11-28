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
 * yuan
 * 2019/10/22
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
        Toast.makeText(getContext(), "no!!",Toast.LENGTH_LONG).show();
        homePresenter.init();
    }

    @Override
    public void setAdapterForListView() {
        Log.d("-------------","hello!!!!");
        list_home = new ArrayList<>();
        item_item = new ArrayList<>();
        homeItem = new HomeItem();
        homeItemsItem = new HomeItemsItem();

        //图书馆
        homeItemsItem.setItem("图书检索");
        item_item.add(homeItemsItem);
        homeItemsItem.setItem("预约研讨室");
        item_item.add(homeItemsItem);
        Log.d("HomeFragement:item_item", item_item.toString());
        homeItem.setFunc("图书馆");
        homeItem.setFuncItem(item_item);
        list_home.add(homeItem);
        Log.d("HomeFragement:list_home", list_home.toString());


        //预约
        homeItemsItem.setItem("预约场地");
        item_item.add(homeItemsItem);
        homeItemsItem.setItem("预约教室");
        item_item.add(homeItemsItem);
        homeItem.setFunc("预约");
        homeItem.setFuncItem(item_item);
        list_home.add(homeItem);
        Log.d("HomeFragement:item_item", item_item.toString());
        Log.d("HomeFragement:list_home", list_home.toString());

        //流量监控
        homeItemsItem.setItem("查看剩余流量");
        item_item.add(homeItemsItem);
        homeItemsItem.setItem("查看已用流量");
        item_item.add(homeItemsItem);
        homeItem.setFunc("流量监控");
        homeItem.setFuncItem(item_item);
        list_home.add(homeItem);
        Log.d("HomeFragement:item_item", item_item.toString());
        Log.d("HomeFragement:list_home", list_home.toString());

        homeLvAdapter = new HomeListViewAdapter(getContext(), R.layout.item_home, list_home);
        lv_home.setAdapter(homeLvAdapter);
    }
}
