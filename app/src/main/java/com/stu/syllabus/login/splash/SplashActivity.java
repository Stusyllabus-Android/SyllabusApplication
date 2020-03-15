package com.stu.syllabus.login.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.stu.syllabus.App;
import com.stu.syllabus.DataBaseHelper;
import com.stu.syllabus.R;
import com.stu.syllabus.main.MainActivity;
import com.stu.syllabus.login.login.LoginActivity;

import javax.inject.Inject;

/**
 * yuan
 * 2019/10/22
 **/
public class SplashActivity extends AppCompatActivity implements SplashContract.view{
    private String TAG = this.getClass().getSimpleName();

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSplashComponent.builder()
                .appComponent(((App) getApplication()).getAppComponent())
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);

        splashPresenter.init();
    }

    @Override
    public void toLoginView() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        SplashActivity.this.finish();
    }

    @Override
    public void toMainView() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        SplashActivity.this.finish();
    }
}
