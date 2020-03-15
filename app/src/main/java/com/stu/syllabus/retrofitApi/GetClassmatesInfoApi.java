package com.stu.syllabus.retrofitApi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * yuan
 * 2019/12/26
 **/
public interface GetClassmatesInfoApi {
    @GET("http://credit.stu.edu.cn/Info/DisplayKkb.aspx?")
    Observable<String> getClassmatesInfo(@Query("ClassID") long lessonID);
}
