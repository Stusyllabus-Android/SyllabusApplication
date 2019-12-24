package com.stu.syllabus.information;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.adapter.OAListAdapter;
import com.stu.syllabus.adapter.OASearchAdapter;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.bean.OASearchBean;
import com.stu.syllabus.information.search.DaggerOAModelComponent;
import com.stu.syllabus.information.search.OASearchContract;
import com.stu.syllabus.information.search.OASearchPresenter;


import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

import static com.stu.syllabus.App.getContext;
/*@author cxy
 * by2019/12/24
 * */
public class OASearch extends BaseActivity implements OASearchContract.view{
   @BindView(R.id.action_search)
    SearchView searchView;
    @BindView(R.id.oaRefresh)
    SwipeRefreshLayout oaRefreshLayout;
    @BindView(R.id.lv)
    ListView listView;
    String a;
    AppComponent mappComponent;


    @Inject
    OASearchPresenter mOASearchPresenter;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DaggerOAModelComponent.builder()
                .appComponent(appComponent)
                .oAModelModule(new com.stu.syllabus.information.OAModelModule(this))
                .build()
                .inject(this);

        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("查找");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("query",query);
                mOASearchPresenter.init(query);

                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

    }

        @Override
    protected int getContentView() {
        return R.layout.activity_oasearch;
    }

    @Override
    public void showOASearch(List<OASearchBean> oasearchbean) {
        listView.setAdapter(new OASearchAdapter(getContext(), R.layout.item_oa_search, oasearchbean));

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
                intent.setClass(OASearch.this, OADetailActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void isRefresh(boolean flag) {
        oaRefreshLayout.setRefreshing(flag);
    }
}
