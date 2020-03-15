package com.stu.syllabus.main.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.login.login.LoginActivity;
import com.stu.syllabus.person.DaggerPersonComponent;
import com.stu.syllabus.person.PersonContract;
import com.stu.syllabus.person.PersonModule;
import com.stu.syllabus.person.PersonPresenter;
import com.stu.syllabus.person.personal.PersonalActivity;
import com.stu.syllabus.person.setting.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * yuan
 * 2019/10/22
 **/
public class PersonFragment extends BaseFragment implements PersonContract.view {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.settingLayout)
    RelativeLayout settingLayout;
    @BindView(R.id.headImageDraweeView)
    SimpleDraweeView headImageDraweeView;
    @BindView(R.id.nicknameTextView)
    TextView nicknameTextView;
    @BindView(R.id.idTextView)
    TextView idTextView;
    @BindView(R.id.signatureTextView)
    TextView signatureTextView;
    @BindView(R.id.changeSemesterLayout)
    RelativeLayout changeSemesterLayout;
    @BindView(R.id.semesterTextView)
    TextView semesterTextView;
    @BindView(R.id.toPersonalActivity)
    LinearLayout toPersonalActivity;

    private AppComponent appComponent;

    @Inject
    PersonPresenter personPresenter;

    private ArrayList<String> years;
    private ArrayList<String> semester;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = this.getComponent(AppComponent.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerPersonComponent.builder()
                .appComponent(appComponent)
                .personModule(new PersonModule(this))
                .build()
                .inject(this);

//        personPresenter.init();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        personPresenter.init();
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_person;
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.person);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }

    @OnClick({R.id.toPersonalActivity, R.id.changeSemesterLayout, R.id.settingLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toPersonalActivity: startActivity(PersonalActivity.getIntent(getContext()));
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); break;
            case R.id.changeSemesterLayout: showSemesterSelect(); break;
            case R.id.settingLayout: startActivityForResult(SettingActivity.getIntent(getContext()), 1000);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); break;
        }
    }

    private void showSemesterSelect() {
        ArrayList<String> none = new ArrayList<>();
        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i1, int i2, View view) {
                semesterTextView.setText(years.get(i) + " " + semester.get(i2));
                Log.d(TAG, "onOptionsSelect: ");
                personPresenter.updateCurrentSemester(years.get(i) + " " + semester.get(i2));
            }
        }).setTitleText("选择学期")
                .setContentTextSize(18)
                .setDividerColor(Color.GRAY)
                .setSelectOptions(0, 0)
                .setBgColor(Color.WHITE)
                .isRestoreItem(true)
                .isCenterLabel(false)
                .build();
        optionsPickerView.setNPicker(years, none, semester);
        optionsPickerView.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null) return;
        if (requestCode == 1000) {
            String value = data.getStringExtra("finishMainActivity");
            if(value.equals("finishMainActivity")){
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        }
    }

    @Override
    public void init() {
        super.init();
        years = new ArrayList<>();
        semester = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0 : years.add("2015-2016"); break;
                case 1 : years.add("2016-2017"); break;
                case 2 : years.add("2017-2018"); break;
                case 3 : years.add("2018-2019"); break;
                case 4 : years.add("2019-2020"); break;
            }
        }
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0 : semester.add("春季学期"); break;
                case 1 : semester.add("秋季学期"); break;
                case 2 : semester.add("夏季学期"); break;
            }
        }
    }

    @Override
    public void init(String id, String avatar, String nickname, String signature, String currentSemester) {
        idTextView.setText("账号：" + id);
        headImageDraweeView.setImageURI(avatar);
        nicknameTextView.setText(nickname);
        if (signature != null && !signature.isEmpty()) {
            signatureTextView.setText(signature);
        }

        if (currentSemester.equals("Non-existent")) {
            semesterTextView.setText("未设置当前学期");
        } else semesterTextView.setText(currentSemester);
    }
}
