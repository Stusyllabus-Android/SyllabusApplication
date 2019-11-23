package com.stu.syllabus.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.yalantis.phoenix.PullToRefreshView;

/**
 * yuan
 * 2019/11/23
 **/

//为避免下拉刷新与滑动产生滑动冲突
public class SyllabusScrollView extends ScrollView {

    private PullToRefreshView pullToRefreshView;

    public SyllabusScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSwipeRefreshLayout(PullToRefreshView pullToRefreshView) {
        this.pullToRefreshView = pullToRefreshView;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (t != 0) {
            pullToRefreshView.setEnabled(false);
        } else {
            pullToRefreshView.setEnabled(true);
        }
    }
}
