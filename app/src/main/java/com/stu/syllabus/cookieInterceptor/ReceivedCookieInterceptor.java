package com.stu.syllabus.cookieInterceptor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.stu.syllabus.App;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * yuan
 * 2019/10/25
 **/
public class ReceivedCookieInterceptor implements Interceptor {
    private String TAG = this.getClass().getSimpleName();

    private String fileName;
    public ReceivedCookieInterceptor(String fileName) {
        super();
        this.fileName = fileName;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            HashSet<String> cookies = new HashSet<>();
            for(String header: originalResponse.headers("Set-Cookie"))
            {
                Log.d(TAG, "intercept: 拦截的cookie是："+header);
                cookies.add(header);
            }
            //保存的sharepreference文件名为cookieData
            SharedPreferences sharedPreferences = App.getContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Log.d(TAG, "intercept: 将cookie存储到" + fileName);
            editor.putStringSet("cookie", cookies);
            editor.commit();
        }

        return originalResponse;
    }
}
