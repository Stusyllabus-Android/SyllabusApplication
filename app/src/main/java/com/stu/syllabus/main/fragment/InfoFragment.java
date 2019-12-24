package com.stu.syllabus.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.adapter.OAListAdapter;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.bean.OAArticle;
import com.stu.syllabus.information.DaggerOAComponent;
import com.stu.syllabus.information.OAContract;
import com.stu.syllabus.information.OADetailActivity;
import com.stu.syllabus.information.OAModule;
import com.stu.syllabus.information.OAPresenter;
import com.stu.syllabus.information.OASearch;

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
        setHasOptionsMenu(true);
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
        toolbar.inflateMenu(R.menu.oa_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_1:
                        Intent intent1 =new Intent();
                         intent1.setClass(getActivity(), OASearch.class);
                         startActivity(intent1);
                        break;

            }
                return true;
        }});
    }

    @Override
    public void showOA(List<OAArticle> oaArticleList) {
        listView.setAdapter(new OAListAdapter(getContext(), R.layout.item_oa, oaArticleList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

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
