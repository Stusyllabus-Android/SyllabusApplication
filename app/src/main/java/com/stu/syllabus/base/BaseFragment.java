package com.stu.syllabus.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.stu.syllabus.di.HasComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * yuan
 * 2019/10/22
 **/
public abstract class BaseFragment extends Fragment {
    public String TAG = this.getClass().getSimpleName();
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        unbinder = ButterKnife.bind(this, view);
        setToolBarTitle();
        init();
        return view;
    }
    
    public abstract int getContentView();

    public abstract void setToolBarTitle();

    public void init(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
