package com.stu.syllabus.bean;

/**
 * yuan
 * 2019/10/22
 **/
public class Oauth {
    /**
     * {
     *     "state": "53a254ea-fba8-4d79-a302-56dd5f097abf",
     *     "client_id": "stu",
     *     "redirect_uri": "https://syllabus.candycute.cn/user/stu_login",
     *     "oauth_url": "https://oauth.candycute.cn/oauth/authorize?response_type=code&client_id=stu&redirect_uri=https://syllabus.candycute.cn/user/stu_login&state=53a254ea-fba8-4d79-a302-56dd5f097abf&scope=*&from=Android",
     *     "scope": "*",
     *     "code": "0"
     * }
     */
    private String state;
    String client_id;
    String redirect_uri;
    String oauth_url;
    String scope;
    String code;

    public String getState() {
        return state;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public String getOauth_url() {
        return oauth_url;
    }

    public String getScope() {
        return scope;
    }

    public String getCode() {
        return code;
    }
}
