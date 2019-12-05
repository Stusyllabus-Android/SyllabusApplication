package com.stu.syllabus.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stu.syllabus.App;
import com.stu.syllabus.AppComponent;
import com.stu.syllabus.di.HasComponent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseOAActivity extends AppCompatActivity {
    public String TAG = this.getClass().getSimpleName();

    public AppComponent appComponent;
    private Unbinder unbinder;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = ((App) getApplication()).getAppComponent();
        setContentView(getContentView());
        ButterKnife.bind(this);
    }
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        unbinder = ButterKnife.bind(this, view);
        setToolBarTitle();
        init();
        return view;
    }
    protected abstract int getContentView();
    public void setupTitleBar(Toolbar toolbar) {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public abstract void setToolBarTitle();

    public void init(){

    }
    
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    protected abstract Object getActivity();
}
