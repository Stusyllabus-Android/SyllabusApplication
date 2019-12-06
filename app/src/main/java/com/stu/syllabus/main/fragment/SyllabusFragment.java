package com.stu.syllabus.main.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.stu.syllabus.AppComponent;
import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseFragment;
import com.stu.syllabus.bean.ShowLessonBean;
import com.stu.syllabus.bean.YiBanTimeTable;
import com.stu.syllabus.syllabus.DaggerSyllabusComponent;
import com.stu.syllabus.syllabus.SyllabusContract;
import com.stu.syllabus.syllabus.SyllabusModule;
import com.stu.syllabus.syllabus.SyllabusPresenter;
import com.stu.syllabus.util.ColorUtil;
import com.stu.syllabus.util.DensityUtil;
import com.stu.syllabus.util.ToastUtil;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * yuan
 * 2019/10/22
 **/
public class SyllabusFragment extends BaseFragment implements SyllabusContract.view{

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
    @BindView(R.id.dateLinearLayout)
    LinearLayout mDateLinearLayout;
    @BindView(R.id.timeLinearLayout)
    LinearLayout mTimeLinearLayout;
    @BindView(R.id.gridLayout)
    GridLayout gridLayout;
    TextView mTopBlankView;

    @Inject
    SyllabusPresenter syllabusPresenter;

    int gridWidth;
    int gridHeight;
    int timeWidth;
    private int detailTimeWidth;
    private String currentSemester;

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

        DaggerSyllabusComponent.builder()
                .appComponent(appComponent)
                .syllabusModule(new SyllabusModule(this))
                .build()
                .inject(this);
        Log.d(TAG, "onCreateView: ");

        syllabusPresenter.init();

        gridWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth() * 2 / 15;
        timeWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth() - gridWidth * 7;
        gridHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight() / 12;
        detailTimeWidth = getResources().getDimensionPixelOffset(R.dimen.detail_time_width);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setupToolbar() {
        setToolBarTitle("第 1 周");
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
        showDate();
        showTime();
        initGridLayout();
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        currentSemester = syllabusPresenter.getCurrentSemester();
        gridLayout.setColumnCount(7);
        gridLayout.setRowCount(13);
    }

    private void showDate() {
        {
            mTopBlankView = (TextView) getActivity().getLayoutInflater()
                    .inflate(R.layout.week_grid, null, false);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    detailTimeWidth, ViewGroup.LayoutParams.MATCH_PARENT
            );
            mTopBlankView.setText("上课时间");
            mDateLinearLayout.addView(mTopBlankView, layoutParams);
            mTopBlankView.setVisibility(View.GONE);
        }

        {
            TextView blankTextView = (TextView) getActivity().getLayoutInflater()
                    .inflate(R.layout.week_grid, null, false);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    timeWidth, ViewGroup.LayoutParams.MATCH_PARENT
            );
            mDateLinearLayout.addView(blankTextView, layoutParams);
        }

        for (int i = 0; i < 7; i++) {
            String[] weekString = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

            TextView weekTextView = (TextView) getActivity().getLayoutInflater()
                    .inflate(R.layout.week_grid, null, false);
            weekTextView.setText(weekString[i]);
            if (i + 1 == 7) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    weekTextView.setBackground(getResources().getDrawable(R.drawable.bg_grid_week_end));
                } else {
                    weekTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_grid_week_end));
                }
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    gridWidth, ViewGroup.LayoutParams.MATCH_PARENT);
            mDateLinearLayout.addView(weekTextView, layoutParams);
        }
    }

    private void showTime() {
        for (int i = 1; i <= 13; i++) {
            TextView timeTextView = (TextView) LayoutInflater
                    .from(getActivity()).inflate(R.layout.week_grid, null, false);
            timeTextView.setText(time2char(i) + "");
            if (i == 13) {
                timeTextView.setBackground(getResources().getDrawable(R.drawable.bg_grid_time_end));
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    timeWidth, gridHeight);
            mTimeLinearLayout.addView(timeTextView, layoutParams);
        }
    }

    public char time2char(int time) {
        if (time < 10) {
            return (char) (time + '0');
        } else if (time > 10) {
            return (char) ('A' + (time - 11));
        } else {
            return '0';
        }
    }

    private void initGridLayout() {
        gridLayout.removeAllViews();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 13; j++) {
                LinearLayout lessonLinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.lesson_grid, null, false);
                MaterialRippleLayout lessonRippleLayout = (MaterialRippleLayout) lessonLinearLayout.findViewById(R.id.lessonRipple);
                TextView lessonTextView = (TextView) lessonLinearLayout.findViewById(R.id.lessonTextView);
                lessonLinearLayout.setVisibility(View.INVISIBLE);
                lessonRippleLayout.setEnabled(false);
                lessonTextView.setWidth(gridWidth);
                lessonTextView.setHeight(gridHeight);
                GridLayout.Spec rowSpec = GridLayout.spec(j, 1);
                GridLayout.Spec columnSpec = GridLayout.spec(i);
                gridLayout.addView(lessonLinearLayout, new GridLayout.LayoutParams(rowSpec, columnSpec));
            }
        }
    }

    @Override
    public void showSyllabus(List<ShowLessonBean> lessonBeanList) {
        Log.d(TAG, "showSyllabus: " + lessonBeanList.size());

        // 遍历转换格式后的课程并添加至格子
        int i = 0;
        for (ShowLessonBean showLessonBean : lessonBeanList) {
            showLessonBean.setBgColor(ColorUtil.bgColors[i++ % ColorUtil.bgColors.length]);
            String[] week = showLessonBean.getDuration().split("-");
            int startWeek = Integer.parseInt(week[0]);
            int endWeek = Integer.parseInt(week[1]);

            if (startWeek > 1 || endWeek < 1) continue;

            // TODO: 2019/12/5 写了大量重复的代码
            if (showLessonBean.getDays().getW0() != null && !showLessonBean.getDays().getW0().equals("-")) {
                Log.d(TAG, "showSyllabus: 周日" + showLessonBean.getDays().getW0());
            }
            if (showLessonBean.getDays().getW1() != null && !showLessonBean.getDays().getW1().equals("-")) {
                Log.d(TAG, "showSyllabus: 周一" + showLessonBean.getDays().getW1());
                String[] time = showLessonBean.getDays().getW1().split("-");
                final LinearLayout lessonLinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.lesson_grid, null, false);
                MaterialRippleLayout lessonRippleLayout = (MaterialRippleLayout) lessonLinearLayout.findViewById(R.id.lessonRipple);
                TextView lessonTextView = (TextView) lessonLinearLayout.findViewById(R.id.lessonTextView);
                lessonTextView.setText(showLessonBean.getName() + "\n@" + showLessonBean.getRoom());
                lessonTextView.setWidth(gridWidth);
                lessonTextView.setHeight(gridHeight * (Integer.parseInt(time[1]) - Integer.parseInt(time[0]) + 1));
                GradientDrawable shape = (GradientDrawable) getResources().getDrawable(R.drawable.grid_background);
                shape.setColor(ColorUtils.setAlphaComponent(getResources().getColor(
                        showLessonBean.getBgColor()), 192));
                lessonTextView.setBackgroundDrawable(shape);
                GridLayout.Spec rowSpec = GridLayout.spec(Integer.parseInt(time[0]) - 1, Integer.parseInt(time[1]) - Integer.parseInt(time[0]) + 1);
                GridLayout.Spec columnSpec = GridLayout.spec(1);
                gridLayout.addView(lessonLinearLayout, new GridLayout.LayoutParams(rowSpec, columnSpec));
            }
            if (showLessonBean.getDays().getW2() != null && !showLessonBean.getDays().getW2().equals("-")) {
                Log.d(TAG, "showSyllabus: 周二" + showLessonBean.getDays().getW2());
                String[] time = showLessonBean.getDays().getW2().split("-");
                final LinearLayout lessonLinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.lesson_grid, null, false);
                MaterialRippleLayout lessonRippleLayout = (MaterialRippleLayout) lessonLinearLayout.findViewById(R.id.lessonRipple);
                TextView lessonTextView = (TextView) lessonLinearLayout.findViewById(R.id.lessonTextView);
                lessonTextView.setText(showLessonBean.getName() + "\n@" + showLessonBean.getRoom());
                Log.d(TAG, "showSyllabus: " + showLessonBean.getRoom());
                lessonTextView.setWidth(gridWidth);
                lessonTextView.setHeight(gridHeight * (Integer.parseInt(time[1]) - Integer.parseInt(time[0]) + 1));
                GradientDrawable shape = (GradientDrawable) getResources().getDrawable(R.drawable.grid_background);
                shape.setColor(ColorUtils.setAlphaComponent(getResources().getColor(
                        showLessonBean.getBgColor()), 192));
                lessonTextView.setBackgroundDrawable(shape);
                GridLayout.Spec rowSpec = GridLayout.spec(Integer.parseInt(time[0]) - 1, Integer.parseInt(time[1]) - Integer.parseInt(time[0]) + 1);
                GridLayout.Spec columnSpec = GridLayout.spec(2);
                gridLayout.addView(lessonLinearLayout, new GridLayout.LayoutParams(rowSpec, columnSpec));
            }
            if (showLessonBean.getDays().getW3() != null && !showLessonBean.getDays().getW3().equals("-")) {
                Log.d(TAG, "showSyllabus: 周三" + showLessonBean.getDays().getW3());
                String[] time = showLessonBean.getDays().getW3().split("-");
                final LinearLayout lessonLinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.lesson_grid, null, false);
                MaterialRippleLayout lessonRippleLayout = (MaterialRippleLayout) lessonLinearLayout.findViewById(R.id.lessonRipple);
                TextView lessonTextView = (TextView) lessonLinearLayout.findViewById(R.id.lessonTextView);
                lessonTextView.setText(showLessonBean.getName() + "\n@" + showLessonBean.getRoom());
                lessonTextView.setWidth(gridWidth);
                lessonTextView.setHeight(gridHeight * (Integer.parseInt(time[1]) - Integer.parseInt(time[0]) + 1));
                GradientDrawable shape = (GradientDrawable) getResources().getDrawable(R.drawable.grid_background);
                shape.setColor(ColorUtils.setAlphaComponent(getResources().getColor(
                        showLessonBean.getBgColor()), 192));
                lessonTextView.setBackgroundDrawable(shape);
                GridLayout.Spec rowSpec = GridLayout.spec(Integer.parseInt(time[0]) - 1, Integer.parseInt(time[1]) - Integer.parseInt(time[0]) + 1);
                GridLayout.Spec columnSpec = GridLayout.spec(3);
                gridLayout.addView(lessonLinearLayout, new GridLayout.LayoutParams(rowSpec, columnSpec));
            }
            if (showLessonBean.getDays().getW4() != null && !showLessonBean.getDays().getW4().equals("-")) {
                Log.d(TAG, "showSyllabus: 周四" + showLessonBean.getDays().getW4());
                String[] time = showLessonBean.getDays().getW4().split("-");
                final LinearLayout lessonLinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.lesson_grid, null, false);
                MaterialRippleLayout lessonRippleLayout = (MaterialRippleLayout) lessonLinearLayout.findViewById(R.id.lessonRipple);
                TextView lessonTextView = (TextView) lessonLinearLayout.findViewById(R.id.lessonTextView);
                lessonTextView.setText(showLessonBean.getName() + "\n@" + showLessonBean.getRoom());
                lessonTextView.setWidth(gridWidth);
                lessonTextView.setHeight(gridHeight * (Integer.parseInt(time[1]) - Integer.parseInt(time[0]) + 1));
                GradientDrawable shape = (GradientDrawable) getResources().getDrawable(R.drawable.grid_background);
                shape.setColor(ColorUtils.setAlphaComponent(getResources().getColor(
                        showLessonBean.getBgColor()), 192));
                lessonTextView.setBackgroundDrawable(shape);
                GridLayout.Spec rowSpec = GridLayout.spec(Integer.parseInt(time[0]) - 1, Integer.parseInt(time[1]) - Integer.parseInt(time[0]) + 1);
                GridLayout.Spec columnSpec = GridLayout.spec(4);
                gridLayout.addView(lessonLinearLayout, new GridLayout.LayoutParams(rowSpec, columnSpec));
            }
            if (showLessonBean.getDays().getW5() != null && !showLessonBean.getDays().getW5().equals("-")) {
                Log.d(TAG, "showSyllabus: 周五" + showLessonBean.getDays().getW5());
                String[] time = showLessonBean.getDays().getW5().split("-");
                final LinearLayout lessonLinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.lesson_grid, null, false);
                MaterialRippleLayout lessonRippleLayout = (MaterialRippleLayout) lessonLinearLayout.findViewById(R.id.lessonRipple);
                TextView lessonTextView = (TextView) lessonLinearLayout.findViewById(R.id.lessonTextView);
                lessonTextView.setText(showLessonBean.getName() + "\n@" + showLessonBean.getRoom());
                lessonTextView.setWidth(gridWidth);
                lessonTextView.setHeight(gridHeight * (Integer.parseInt(time[1]) - Integer.parseInt(time[0]) + 1));
                GradientDrawable shape = (GradientDrawable) getResources().getDrawable(R.drawable.grid_background);
                shape.setColor(ColorUtils.setAlphaComponent(getResources().getColor(
                        showLessonBean.getBgColor()), 192));
                lessonTextView.setBackgroundDrawable(shape);
                GridLayout.Spec rowSpec = GridLayout.spec(Integer.parseInt(time[0]) - 1, Integer.parseInt(time[1]) - Integer.parseInt(time[0]) + 1);
                GridLayout.Spec columnSpec = GridLayout.spec(5);
                gridLayout.addView(lessonLinearLayout, new GridLayout.LayoutParams(rowSpec, columnSpec));
            }
            if (showLessonBean.getDays().getW6() != null && !showLessonBean.getDays().getW6().equals("-")) {
                Log.d(TAG, "showSyllabus: 周六" + showLessonBean.getDays().getW6());
            }
        }
    }

    @Override
    public void showMsg(String message) {
        ToastUtil.showShort(getContext(), message);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Log.d(TAG, "onCreateOptionsMenu: ");
        toolbar.getMenu().clear();
        inflater.inflate(R.menu.syllabus_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: ");
        switch (item.getItemId()) {
            case R.id.addLesson: ToastUtil.showShort(getContext(), "添加课表"); break;
            case R.id.deleteLesson: ToastUtil.showShort(getContext(), "删除课表"); break;
            case R.id.shareSyllabus: ToastUtil.showShort(getContext(), "分享课表"); break;
            case R.id.setWallPaper: ToastUtil.showShort(getContext(), "设置壁纸"); break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
