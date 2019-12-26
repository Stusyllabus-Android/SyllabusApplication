package com.stu.syllabus.retrofitApi;

import com.stu.syllabus.bean.OASearchBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetOASearch {
    @GET("oauth/oa/search")
    Observable<List<OASearchBean>> getOASearch(@Query("keyword") String keyword, @Query("row_start") int row_start, @Query("row_end") int row_end);
}
