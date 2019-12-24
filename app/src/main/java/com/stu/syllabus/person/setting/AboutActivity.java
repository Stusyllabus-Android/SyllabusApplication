package com.stu.syllabus.person.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;

import butterknife.BindView;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
//    @BindView(R.id.webView)
//    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_about;
    }

    @Override
    protected void init() {
        super.init();
        setupTitleBar(toolbar);
//        webView.loadUrl("file:///android_asset/about.html");
    }

    @Override
    public void setupTitleBar(Toolbar toolbar) {
        super.setupTitleBar(toolbar);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, AboutActivity.class);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
}
