package com.stu.syllabus.widget;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.stu.syllabus.R;

/**
 * yuan
 * 2019/11/25
 **/
public abstract class BaseBottomDialog extends DialogFragment {

    private static final float DEFAULT_DIM = 0.2f;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(getCancelOutside());

        View view = inflater.inflate(getLayoutRes(), container, false);
        bindView(view);
        return view;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void bindView(View view);

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        params.dimAmount = getDimAmount();

        params.width = WindowManager.LayoutParams.WRAP_CONTENT;

        params.height = (getHeight() > 0) ?  getHeight() :  WindowManager.LayoutParams.WRAP_CONTENT;

        params.gravity = Gravity.BOTTOM;

        window.setAttributes(params);
    }

    private int getHeight() {
        return -1;
    }

    private float getDimAmount() {
        return DEFAULT_DIM;
    }

    public boolean getCancelOutside() {
        return true;
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, this.getClass().getSimpleName());
    }
}
