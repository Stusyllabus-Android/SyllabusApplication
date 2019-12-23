package com.stu.syllabus.person.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;

import butterknife.BindView;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView webView;

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
        webView.loadUrl("file:///android_asset/about.html");
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, AboutActivity.class);
    }
}
