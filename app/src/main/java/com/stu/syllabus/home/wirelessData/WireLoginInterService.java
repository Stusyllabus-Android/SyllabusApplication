package com.stu.syllabus.home.wirelessData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by daidaijie on 2016/9/3.
 */
public interface WireLoginInterService {

    @FormUrlEncoded
    @POST("login.php")
    Observable<String> loginInternet(@Field("opr") String opr,
                                     @Field("userName") String username,
                                     @Field("pwd") String password,
                                     @Field("rememberPwd") String isRemember);
}
