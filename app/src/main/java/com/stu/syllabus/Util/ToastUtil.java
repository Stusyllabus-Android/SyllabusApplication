package com.stu.syllabus;

import android.content.Context;
import android.widget.Toast;

/**
 * yuan
 * 2019/10/23
 **/
public class ToastUtil {
    public static void showShort(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }
}
