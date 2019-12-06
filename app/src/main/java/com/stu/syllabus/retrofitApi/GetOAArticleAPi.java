package com.stu.syllabus.retrofitApi;

import com.stu.syllabus.bean.OAArticle;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * yuan
 * 2019/11/28
 **/
public interface GetOAArticleAPi {
    @GET("oauth/oa/list")
    Observable<List<OAArticle>> getOAArticle(@Query("row_start") int row_start, @Query("row_end") int row_end, @Query("subcompany_id") int subcompany_id);
}
