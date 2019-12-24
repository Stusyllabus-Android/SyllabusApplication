package com.stu.syllabus.syllabus.lessoninfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.stu.syllabus.bean.ShowLessonBean;

import butterknife.BindView;

public class LessonInfoActivity extends BaseActivity {

    @BindView(R.id.wrap_container)
    LinearLayout wrapContainer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lessonName)
    TextView nameTextView;
    @BindView(R.id.lessonID)
    TextView idTextView;
    @BindView(R.id.lessonCredit)
    TextView creditTextView;
    @BindView(R.id.lessonClassroom)
    TextView classroomTextView;
    @BindView(R.id.lessonTeacher)
    TextView teacherTextView;
    @BindView(R.id.lessonTime)
    TextView timeTextView;
    @BindView(R.id.toClassmatesAct)
    Button toClassMatesAct;

    private int color;
    private String name;
    private String id;
    private String credit;
    private String classroom;
    private String teacher;
    private String days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        color = intent.getIntExtra("color", 0);
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        credit = intent.getStringExtra("credit");
        classroom = intent.getStringExtra("classroom");
        teacher = intent.getStringExtra("teacher");
        days = intent.getStringExtra("days");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        super.init();
        setupTitleBar(toolbar);
        nameTextView.setText(name);
        idTextView.setText(id);
        creditTextView.setText(credit);
        classroomTextView.setText(classroom);
        teacherTextView.setText(teacher);
        timeTextView.setText(days);
        wrapContainer.setBackgroundColor(getResources().getColor(color));
        toClassMatesAct.setBackgroundColor(getResources().getColor(color));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_lesson_info;
    }

    @Override
    public void setupTitleBar(Toolbar toolbar) {
        super.setupTitleBar(toolbar);
    }

    public static Intent getIntent(Context context, int color, String name, String id, String credit, String classroom, String teacher, String days) {
        Intent intent = new Intent(context, LessonInfoActivity.class);
        intent.putExtra("color", color);
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        intent.putExtra("credit", credit);
        intent.putExtra("classroom", classroom);
        intent.putExtra("teacher", teacher);
        intent.putExtra("days", days);
        return intent;
    }
}
