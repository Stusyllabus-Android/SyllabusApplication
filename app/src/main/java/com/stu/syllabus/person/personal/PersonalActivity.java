package com.stu.syllabus.person.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.stu.syllabus.R;
import com.stu.syllabus.base.BaseActivity;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import butterknife.BindView;

public class PersonalActivity extends BaseActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    AboutView aboutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        aboutView = (AboutView) AboutBuilder.with(this)
                .setPhoto(R.drawable.launch_bg)
                .setCover(R.mipmap.google)
                .setName("name")
                .setSubTitle("signature")
                .setLinksAnimated(true)
                .build();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_personal;
    }

    @Override
    protected void init() {
        super.init();
        frameLayout.addView(aboutView);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, PersonalActivity.class);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
}
