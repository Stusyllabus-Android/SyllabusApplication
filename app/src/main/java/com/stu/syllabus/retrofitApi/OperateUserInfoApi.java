package com.stu.syllabus.retrofitApi;

import com.stu.syllabus.bean.PostUserInfoResult;
import com.stu.syllabus.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * yuan
 * 2019/11/25
 **/
public interface OperateUserInfoApi {

    String SUFFIX_URL = "user/oauth/proxy";

    //获取个人信息
    @FormUrlEncoded
    @POST(SUFFIX_URL)
    Observable<UserInfo> getUserInfo(@Header("skey") String skey, @Field("url") String url, @Field("method") String method, @Field("from") String from);

    //修改头像
    @FormUrlEncoded
    @POST(SUFFIX_URL)
    Observable<PostUserInfoResult> postAvatar(@Header("skey") String skey, @Field("url") String url, @Field("method") String method, @Field("from") String from, @Field("avatar_url") String avatar);

    //修改昵称
    @FormUrlEncoded
    @POST(SUFFIX_URL)
    Observable<PostUserInfoResult> postNickname(@Header("skey") String skey, @Field("url") String url, @Field("method") String method, @Field("from") String from, @Field("new_nickname") String nickname);

    //修改个性签名
    @FormUrlEncoded
    @POST(SUFFIX_URL)
    Observable<PostUserInfoResult> postSignature(@Header("skey") String skey, @Field("url") String url, @Field("method") String method, @Field("from") String from, @Field("new_signature") String signature);
}
