package com.stu.syllabus.cookiesInterceptor;

import android.content.Context;
import android.util.Log;

import com.stu.syllabus.App;

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
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> perferences = (HashSet) App.getContext().getSharedPreferences("cookieData", Context.MODE_PRIVATE).getStringSet("cookie", null);
        if (perferences != null) {
            for (String cookie : perferences) {
                builder.addHeader("Cookie", cookie);
                Log.d(TAG, "intercept: " + cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}
