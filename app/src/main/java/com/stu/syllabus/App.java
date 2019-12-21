package com.stu.syllabus;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.SyncStateContract;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * yuan
 * 2019/10/23
 **/
public class App extends Application {
    private static Context context;

    public static int versionCode = Integer.MAX_VALUE;

    public static String versionName = "";

    AppComponent appComponent;

    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = "wx0224899c3b7405ed";

    // IWXAPI 是第三方app和微信通信的openApi接口
    private static IWXAPI api;

    public static IWXAPI getApi() {
        return api;
    }

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
        regToWx();
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

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);

    }

    public static Context getContext() {
        return context;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
