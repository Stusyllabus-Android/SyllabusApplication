package com.stu.syllabus;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * yuan
 * 2019/10/23
 **/
public class App extends Application {
    private static Context context;

    public static int versionCode = Integer.MAX_VALUE;

    public static String versionName = "";

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

        context = getApplicationContext();

        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .utilModule(new UtilModule(this))
                .retrofitModule(new RetrofitModule())
                .build();

        initVersion();
    }

    private void initVersion() {
        try {
            PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionCode = pi.versionCode;
            versionName = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static Context getContext() {
        return context;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
