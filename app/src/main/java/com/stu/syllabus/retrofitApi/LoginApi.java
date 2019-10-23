package com.stu.syllabus.retrofitApi;

import com.stu.syllabus.bean.Login;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * yuan
 * 2019/10/22
 **/
public interface LoginApi {
    @FormUrlEncoded
    @POST("oauth/login")
    Observable<Login> login(@Field("account") String account, @Field("password") String password);
}
