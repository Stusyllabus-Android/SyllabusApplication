package com.stu.syllabus.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.syllabus.DaggerSyllabusComponent;
import com.stu.syllabus.syllabus.SyllabusContract;
import com.stu.syllabus.syllabus.SyllabusModule;
import com.stu.syllabus.syllabus.SyllabusPresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * yuan
 * 2019/10/22
 **/
public class SyllabusFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @BindView(R.id.getRequestToken)
    Button getGetRequestToken;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.getToken)
    Button getToken;
    @BindView(R.id.getTimeTable)
    Button getTimeTable;

    @Inject
    SyllabusPresenter syllabusPresenter;

    private AppComponent appComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = this.getComponent(AppComponent.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerSyllabusComponent.builder()
                .appComponent(appComponent)
                .syllabusModule(new SyllabusModule((SyllabusContract.view) this))
                .build()
                .inject(this);

        syllabusPresenter.init();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_syllabus;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getRequestToken: syllabusPresenter.getGetRequestToken();break;
            case R.id.login: syllabusPresenter.login("17xyxie@stu.edu.cn", "xp782278", "stu"); break;
            case R.id.getToken: syllabusPresenter.getToken(); break;
            case R.id.getTimeTable: syllabusPresenter.getTimeTable(); break;
        }
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.syllabus);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }
}
