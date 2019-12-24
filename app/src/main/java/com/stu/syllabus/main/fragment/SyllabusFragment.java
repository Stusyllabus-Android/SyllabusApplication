package com.stu.syllabus.main.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.adapter.SyllabusViewPagerAdapter;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.syllabus.syllabusmain.DaggerSyllabusMainComponent;
import com.stu.syllabus.syllabus.syllabusmain.SyllabusMainContract;
import com.stu.syllabus.syllabus.syllabusmain.SyllabusMainModule;
import com.stu.syllabus.syllabus.syllabusmain.SyllabusMainPresenter;
import com.stu.syllabus.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * yuan
 * 2019/10/22
 * SyllabusFragment对应着syllabus包中的syllabusmain包
 **/
public class SyllabusFragment extends BaseFragment implements SyllabusMainContract.view{

    @BindView(R.id.rootLayout)
    LinearLayout rootLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.titleTextView)
    TextView mTitleTextView;
    @BindView(R.id.weekTitleLayout)
    LinearLayout mWeekTitleLayout;
    @BindView(R.id.showWeekSelectImageView)
    ImageView mShowWeekSelectImageView;
    @BindView(R.id.syllabusViewPager)
    ViewPager mSyllabusViewPager;

    @Inject
    SyllabusMainPresenter syllabusPresenter;

    private int pageIndex = 0;

    private AppComponent appComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appComponent = this.getComponent(AppComponent.class);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        DaggerSyllabusMainComponent.builder()
                .appComponent(appComponent)
                .syllabusMainModule(new SyllabusMainModule(this))
                .build()
                .inject(this);

        syllabusPresenter.init();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setupToolbar() {
        setToolBarTitle("第 " + (pageIndex + 1) + " 周");
        toolbar.setTitle("");
    }

    private void setToolBarTitle(String title) {
        mTitleTextView.setText(title);
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitle));
    }

    public void showSelectWeekLayout(boolean isShow) {

    }

    @Override
    public int getContentView() {
        return R.layout.fragment_syllabus;
    }

    @Override
    public void init() {
        setupToolbar();
        setupViewPager();
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void setupViewPager() {
        pageIndex = 0;
        mSyllabusViewPager.setAdapter(new SyllabusViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        mSyllabusViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position
                    , float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pageIndex = position;
                setToolBarTitle("第 " + (position + 1) + " 周");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void showMsg(String message) {
        ToastUtil.showShort(getContext(), message);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        toolbar.getMenu().clear();
        inflater.inflate(R.menu.syllabus_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addLesson: ToastUtil.showShort(getContext(), "添加课表"); break;
            case R.id.deleteLesson: ToastUtil.showShort(getContext(), "删除课表"); break;
            case R.id.shareSyllabus: ToastUtil.showShort(getContext(), "分享课表"); break;
            case R.id.showLessonTime: ToastUtil.showShort(getContext(), "上课时间"); break;
            case R.id.showExaminationTime: ToastUtil.showShort(getContext(), "考试时间"); break;
            case R.id.setWallPaper: ToastUtil.showShort(getContext(), "设置壁纸"); break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void changeWallPaper() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
