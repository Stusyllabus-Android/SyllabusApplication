package com.stu.syllabus.retrofitApi;

import com.stu.syllabus.bean.Skey;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * yuan
 * 2019/10/23
 **/
public interface GetSkeyApi {
    @GET("user/stu_login")
    Observable<Skey> getSkey(@Query("code") String code, @Query("state") String state, @Query("from") String from);
}
