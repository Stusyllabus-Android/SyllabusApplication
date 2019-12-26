package com.stu.syllabus.retrofitApi;


import com.stu.syllabus.bean.WireLoginInfo;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by daidaijie on 2016/8/28.
 */
public interface SchoolInternetApi {

    @POST("userflux")
    Observable<String> getInternetInfo();

}
