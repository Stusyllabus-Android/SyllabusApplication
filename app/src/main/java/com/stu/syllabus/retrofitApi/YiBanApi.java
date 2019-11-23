package com.stu.syllabus.retrofitApi;

import com.stu.syllabus.bean.YiBanTimeTable;
import com.stu.syllabus.bean.YiBanToken;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * yuan
 * 2019/11/12
 **/
public interface YiBanApi {

    @GET("web/Account/Login")
    Observable<String> getRequestToken();

    @FormUrlEncoded
    @POST("web/Account/Login")
    Observable<String> login(@Field("Email") String email, @Field("Password") String password, @Field("__RequestVerificationToken") String requestToken);

    @GET("web/Alipay/GetToken")
    Observable<YiBanToken> getToken();

    @GET("api/api/creditstudenttimetable/")
    Observable<YiBanTimeTable> getTimeTable(@Query("vid") long vid,
                                            @Query("timestamp")long timestamp,
                                            @Query("app") String app,
                                            @Query("nonce") String nonce,
                                            @Query("token") String token);
}
