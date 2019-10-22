package com.stu.syllabus.retrofitApi;

import com.stu.syllabus.bean.Login;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {
    @FormUrlEncoded
    @POST("credit/api/v2.1/auth")
    Observable<Login> login(@Field("username") String account, @Field("password") String password);
}
