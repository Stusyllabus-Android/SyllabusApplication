package com.stu.syllabus.retrofitApi;

import com.stu.syllabus.bean.Login;
import com.stu.syllabus.bean.Oauth;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginApi {
    @GET("user/get_oauth_data?from=Android")
    Observable<Oauth> getOauth();
}
