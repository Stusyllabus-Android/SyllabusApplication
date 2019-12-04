package com.stu.syllabus.util;

import android.content.Context;

/**
 * yuan
 * 2019/12/2
 **/
public class DensityUtil {
    public static int pxToDp (Context context, int pixel) {
        return (int) (pixel / (context.getResources().getDisplayMetrics().density + 0.5f) );
    }
}
