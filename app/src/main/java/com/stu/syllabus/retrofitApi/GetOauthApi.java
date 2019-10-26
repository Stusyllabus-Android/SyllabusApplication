package com.stu.syllabus.retrofitApi;

import com.stu.syllabus.bean.Oauth;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * yuan
 * 2019/10/22
 **/
public interface GetOauthApi {
    @GET("user/get_oauth_data?from=android")
    Observable<Oauth> getOauth();
}
