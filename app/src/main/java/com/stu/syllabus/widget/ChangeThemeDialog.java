package com.stu.syllabus.widget;

import android.view.View;

import com.stu.syllabus.R;

/**
 * yuan
 * 2019/11/26
 **/
public class ChangeThemeDialog extends BaseBottomDialog implements View.OnClickListener {

    @Override
    public void onClick(View v) {

    }

    interface OnThemeSelectCallback {
        void onThemeSelect();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_changetheme;
    }

    @Override
    public void bindView(View view) {

    }
}
