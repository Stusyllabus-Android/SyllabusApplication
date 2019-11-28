package com.stu.syllabus.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.stu.syllabus.App;
import com.stu.syllabus.AppComponent;

import butterknife.ButterKnife;

/**
 * yuan
 * 2019/10/22
 **/
public abstract class BaseActivity extends AppCompatActivity {
    public String TAG = this.getClass().getSimpleName();

    public AppComponent appComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = ((App) getApplication()).getAppComponent();
        setContentView(getContentView());
        ButterKnife.bind(this);
    }

    protected abstract int getContentView();

    public void setupTitleBar(Toolbar toolbar) {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //判断是返回键然后退出当前Activity
            this.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
