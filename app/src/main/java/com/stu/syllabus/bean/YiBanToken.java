package com.stu.syllabus.bean;

import com.google.gson.annotations.SerializedName;

/**
 * yuan
 * 2019/11/12
 **/
public class YiBanToken {
    /**
     * vid : 157337
     * timestamp : 1573560742
     * token : IM37szymlVvUljCKnWDUBGYpYS5WxuzSRFwAIHIBuio=
     * app : stu.web
     * nonce : 53dc5c7d-e84f-45a9-88ec-15e86beca0c7
     */

    @SerializedName("vid")
    public int vid;
    @SerializedName("timestamp")
    public int timestamp;
    @SerializedName("token")
    public String token;
    @SerializedName("app")
    public String app;
    @SerializedName("nonce")
    public String nonce;

    @Override
    public String toString() {
        return "YiBanToken{" +
                "vid=" + vid +
                ", timestamp=" + timestamp +
                ", token='" + token + '\'' +
                ", app='" + app + '\'' +
                ", nonce='" + nonce + '\'' +
                '}';
    }
}
