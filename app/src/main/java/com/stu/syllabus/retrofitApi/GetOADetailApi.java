package com.stu.syllabus.retrofitApi;

import com.stu.syllabus.bean.OADetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetOADetailApi {
    @GET("oauth/oa/details")
    Call<OADetail> getOADetail(@Query("id") String id);
}
