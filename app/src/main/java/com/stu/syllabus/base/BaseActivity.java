package com.stu.syllabus.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    public String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
    }

    protected abstract int getContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
