package com.stu.syllabus.cookiesInterceptor;

import android.content.Context;
import android.util.Log;

import com.stu.syllabus.App;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * yuan
 * 2019/10/25
 **/
public class AddCookiesInterceptor implements Interceptor {
    private String TAG = this.getClass().getSimpleName();

    private String fileName;
    public AddCookiesInterceptor(String fileName) {
        super();
        this.fileName = fileName;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> perferences = (HashSet) App.getContext().getSharedPreferences(fileName, Context.MODE_PRIVATE).getStringSet("cookie", null);
        Log.d(TAG, "intercept: 从" + fileName + "中获取");
        if (perferences != null) {
            for (String cookie : perferences) {
                builder.addHeader("Cookie", cookie);
                Log.d(TAG, "添加的cookie是: " + cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}
