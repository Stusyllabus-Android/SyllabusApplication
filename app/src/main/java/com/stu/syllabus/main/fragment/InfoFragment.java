package com.stu.syllabus.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.adapter.OAListAdapter;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.bean.OAArticle;
import com.stu.syllabus.information.DaggerOAComponent;
import com.stu.syllabus.information.OAContract;
//import com.stu.syllabus.information.OADetailFragment;
import com.stu.syllabus.information.OADetailActivity;
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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @SuppressLint("ResourceType")
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //获取fragment的实例
//                Fragment fragment=new Fragment();
//                //获取Fragment的管理器
//                FragmentManager fragmentManager=getChildFragmentManager();
//                //开启fragment的事物,在这个对象里进行fragment的增删替换等操作。
//                FragmentTransaction ft=fragmentManager.beginTransaction();
//                //跳转到fragment，第一个参数为所要替换的位置id，第二个参数是替换后的fragment
//                ft.replace(R.layout.fragment_oadetail,OADetailFragment);
//                //提交事物
//                ft.commit();
//            }
             String b;

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent();
                Object oa = parent.getAdapter().getItem(position);
               int a=0;
                java.lang.reflect.Method[] method = oa.getClass().getDeclaredMethods();//获取所有方法
                for(java.lang.reflect.Method m:method) {

                    System.out.println(m.getName());
                    if (m.getName().startsWith("get")) {
                        Object o = null;
                        try {
                            o = m.invoke(oa);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (o != null && !"".equals(o.toString())) {
                            a++;
                            Log.i("cxx", o.toString());
                            if(a==2){
                                b=o.toString();
                            }
                        }
                    }}

                intent.putExtra("id",b);//获取点击item的id
                Log.i("c1234531", b);
                intent.setClass(getActivity(), OADetailActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void isRefresh(boolean flag) {
        oaRefreshLayout.setRefreshing(flag);
    }




}
