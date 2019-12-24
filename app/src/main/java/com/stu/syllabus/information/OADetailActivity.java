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


    String id;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupTitleBar(toolbar);

        WebSettings webSettings = mOAWebView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//设置webview推荐使用的窗口
        webSettings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
        webSettings.setDisplayZoomControls(false);//隐藏webview缩放按钮
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//适应屏幕，内容将自动缩放
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//返回按钮的监听
        getSupportActionBar().setHomeButtonEnabled(true);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.i("iddd",id);
        String url= " http://oa.stu.edu.cn/page/maint/template/news/newstemplateprotal.jsp?templatetype=1&templateid=3&docid="+id;
        mOAWebView.loadUrl(url);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_oadetail;
    }





}
