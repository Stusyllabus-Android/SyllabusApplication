package com.stu.syllabus.information;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.base.BaseOAActivity;
import com.stu.syllabus.bean.OADetail;

import javax.inject.Inject;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*@author cxy
 * by2019/12/05
 * */
public class OADetailActivity extends BaseActivity  {
    @BindView(R.id.oAWebView)
    WebView mOAWebView;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    private AppComponent appComponent;
    Call<OADetail> oadetail;
//    @Inject
//    OADetailPresenter oadetailPresenter;
    String id;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        appComponent = this.getComponent(AppComponent.class);
        WebSettings webSettings = mOAWebView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //显示状态栏

        setSupportActionBar(toolbar);//显示ToolBar

//返回按钮的监听

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);
        setToolBarTitle();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.i("iddd",id);
        String url= " http://oa.stu.edu.cn/page/maint/template/news/newstemplateprotal.jsp?templatetype=1&templateid=3&docid="+id;
        mOAWebView.loadUrl(url);
    }
//
//    @Nullable
//    public View onCreatView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
////        DaggerOADetailComponent.builder()
//////                .appComponent(appComponent)
////                .oADetailModule(new OADetailModule(this))
////                .build()
////                .inject(this);
////
////        oadetailPresenter.init();
////        return super.onCreateView(inflater, container, savedInstanceState);
//    }

public void setToolBarTitle() {
    toolbar.setTitle("办公自动化");
    toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
}
    @Override
    public int getContentView() {
        return R.layout.activity_oadetail;
    }

//    @Override
//    public void setToolBarTitle() {
//        toolbar.setTitle("办公自动化");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
//    }
//    @Override
//    public void init() {
//        super.init();
//
//    }

//    @Override
//    protected Object getActivity() {
//        return null;
//    }


    //    @Override
    public void showDetail(Call<OADetail> oadetail) {

    }

}
