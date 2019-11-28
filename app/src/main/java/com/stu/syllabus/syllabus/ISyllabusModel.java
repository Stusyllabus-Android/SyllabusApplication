package com.stu.syllabus.syllabus;

import com.stu.syllabus.bean.BaseUserInfo;
import com.stu.syllabus.bean.YiBanTimeTable;
import com.stu.syllabus.bean.YiBanToken;

import io.reactivex.Observable;

/**
 * yuan
 * 2019/11/12
 **/
public interface ISyllabusModel {

    Observable<BaseUserInfo> getUserInfoFromDisk();

    Observable<String> getRequestToken();

    Observable<String> login(String email, String password, String requestToken);

    Observable<YiBanToken> getToken();

    Observable<YiBanTimeTable> getTimeTable(long vid, long timestamp, String app, String nonce, String token);

}
