package com.stu.syllabus.bean;

import com.google.gson.annotations.SerializedName;

/**
 * yuan
 * 2019/11/25
 **/
//对头像、个性签名、头像进行修改后接口返回的数据
public class PostUserInfoResult {
    /**
     * data : {"code":"0"}
     * status : 200
     * code : 0
     */

    @SerializedName("data")
    public DataBean data;
    @SerializedName("status")
    public int status;
    @SerializedName("code")
    public String code;

    public static class DataBean {
        /**
         * code : 0
         */

        @SerializedName("code")
        public String code;
    }
}
