package com.stu.syllabus.home.wirelessData;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat.Builder;

import com.stu.syllabus.App;
import com.stu.syllabus.R;
import com.stu.syllabus.bean.WirelessInfo;

/**
 * 流量服务
 */
public class WireService extends Service {

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, WireService.class);
        return intent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)  //???minSDK = 16
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        WirelessInfo mStreamInfo = WirelessInfo.getInstance();
        int type = mStreamInfo.getType();

        Intent notificationIntent = new Intent(App.getContext(), WirelessActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                0,
                notificationIntent, 0
        );

        if (type == WirelessInfo.TYPE_SUCCESS && mStreamInfo != null) {
            NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Builder builder = new Builder(this.getApplicationContext(),"channeId");
            double progress = (mStreamInfo.getNowByte() / mStreamInfo.getAllByte()) * 100;

            builder.setProgress(100, (int) progress, false)
                    .setContentTitle(mStreamInfo.getName() + "已用: " +
                            String.format("%.2f%%", progress))
                    .setContentText("已用" + mStreamInfo.getNowStream() + "，总共" + mStreamInfo
                            .getAllStream() + "。状态" + mStreamInfo.getState())
                    .setSmallIcon(R.mipmap.logo)
                    .setContentIntent(contentIntent)
                    .setWhen(System.currentTimeMillis());
            //以下两道注释可被manager代替
//          Notification notification = builder.build();
//          startForeground(101, notification);

            notifyManager.notify(101, builder.build());
            Log.e("ServiceTest", "已连接");
        }
        if (type == WirelessInfo.TYPE_UN_CONNECT) {
            NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
            builder.setContentTitle("网络状态")
                    .setSmallIcon(R.mipmap.logo)
                    .setContentText("没连接到校园网")
                    .setContentIntent(contentIntent)
                    .setWhen(System.currentTimeMillis());
            notifyManager.notify(101, builder.build());

            Log.e("ServiceTest", "已断开");
        }
        if (type == WirelessInfo.TYPE_LOGOUT) {
            Builder builder = new Builder(this.getApplicationContext());

            builder.setContentTitle("网络状态")
                    .setContentIntent(contentIntent)
                    .setSmallIcon(R.mipmap.logo)
                    .setContentText("没登录校园网流量验证")
                    .setWhen(System.currentTimeMillis());
            Notification notification = builder.build();

            startForeground(101, notification);
            Log.e("ServiceTest", "无登录");

        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

