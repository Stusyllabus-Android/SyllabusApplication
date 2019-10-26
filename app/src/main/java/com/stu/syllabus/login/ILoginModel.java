package com.stu.syllabus.login;

import com.stu.syllabus.bean.Authorize;
import com.stu.syllabus.bean.Login;
import com.stu.syllabus.bean.Oauth;
import com.stu.syllabus.bean.Skey;

import io.reactivex.Observable;

/**
 * yuan
 * 2019/10/23
 **/
public interface ILoginModel {
    void saveSkeyToDisk(String skey, String refresh_key);

    String getSkeyFromDisk();

    Observable<Oauth> getOauthFromNet();

    Observable<Login> getLoginFromNet(String account, String password);

    Observable<Authorize> getAuthorizeCodeFromNet(String response_type, String client_id, String state, String scope, String from);

    Observable<Skey> getSkeyFromNet(String code, String state, String from);

}
