package com.stu.syllabus.retrofitApi;

import com.stu.syllabus.bean.Authorize;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * yuan
 * 2019/10/23
 **/
public interface GetAuthorizeCodeApi {
    @GET("oauth/authorise")
    Observable<Authorize> getAuthorizeCode(@Query("response_type") String response_type, @Query("client_id") String client_id, @Query("state") String state, @Query("scope") String scope, @Query("from") String form);
}
