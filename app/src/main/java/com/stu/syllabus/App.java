package com.stu.syllabus;

import android.app.Application;
import android.content.Context;

/**
 * yuan
 * 2019/10/23
 **/
public class App extends Application {
    private static Context context;

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .utilModule(new UtilModule(this))
                .build();
    }

    public static Context getContext() {
        return context;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
