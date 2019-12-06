package com.stu.syllabus.bean;

import com.google.gson.annotations.SerializedName;

/**
 * yuan
 * 2019/11/25
 **/
public class UserInfo {
    /**
     * data : {"user":{"id":"17sfyuan","info":{"name":"袁盛峰","student_num":"2017101086","sex":"男","education_level":"本科","education_year":"4","enrollment":"2017","education_type":"","college":"工学院","dorm":"敬一书院","dorm_num":"C245","state":"当前在校生","from":"广东省湛江市","avatar":"https://i.loli.net/2019/11/25/vGOCTH8Mp4x72jY.jpg","nickname":"Jarvis Yuen","signature":"一个很有个性的签名"}},"code":"0"}
     * status : 200
     * code : 0
     */

    @SerializedName("data")
    public DataBean data;
    @SerializedName("status")
    public int status;
    @SerializedName("code")
    public String code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user : {"id":"17sfyuan","info":{"name":"袁盛峰","student_num":"2017101086","sex":"男","education_level":"本科","education_year":"4","enrollment":"2017","education_type":"","college":"工学院","dorm":"敬一书院","dorm_num":"C245","state":"当前在校生","from":"广东省湛江市","avatar":"https://i.loli.net/2019/11/25/vGOCTH8Mp4x72jY.jpg","nickname":"Jarvis Yuen","signature":"一个很有个性的签名"}}
         * code : 0
         */

        @SerializedName("user")
        public UserBean user;
        @SerializedName("code")
        public String code;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 17sfyuan
             * info : {"name":"袁盛峰","student_num":"2017101086","sex":"男","education_level":"本科","education_year":"4","enrollment":"2017","education_type":"","college":"工学院","dorm":"敬一书院","dorm_num":"C245","state":"当前在校生","from":"广东省湛江市","avatar":"https://i.loli.net/2019/11/25/vGOCTH8Mp4x72jY.jpg","nickname":"Jarvis Yuen","signature":"一个很有个性的签名"}
             */

            @SerializedName("id")
            public String id;
            @SerializedName("info")
            public InfoBean info;

            public InfoBean getInfo() {
                return info;
            }

            public void setInfo(InfoBean info) {
                this.info = info;
            }

            public static class InfoBean {
                /**
                 * name : 袁盛峰
                 * student_num : 2017101086
                 * sex : 男
                 * education_level : 本科
                 * education_year : 4
                 * enrollment : 2017
                 * education_type :
                 * college : 工学院
                 * dorm : 敬一书院
                 * dorm_num : C245
                 * state : 当前在校生
                 * from : 广东省湛江市
                 * avatar : https://i.loli.net/2019/11/25/vGOCTH8Mp4x72jY.jpg
                 * nickname : Jarvis Yuen
                 * signature : 一个很有个性的签名
                 */

                @SerializedName("name")
                public String name;
                @SerializedName("student_num")
                public String studentNum;
                @SerializedName("sex")
                public String sex;
                @SerializedName("education_level")
                public String educationLevel;
                @SerializedName("education_year")
                public String educationYear;
                @SerializedName("enrollment")
                public String enrollment;
                @SerializedName("education_type")
                public String educationType;
                @SerializedName("college")
                public String college;
                @SerializedName("dorm")
                public String dorm;
                @SerializedName("dorm_num")
                public String dormNum;
                @SerializedName("state")
                public String state;
                @SerializedName("from")
                public String from;
                @SerializedName("avatar")
                public String avatar;
                @SerializedName("nickname")
                public String nickname;
                @SerializedName("signature")
                public String signature;

                public InfoBean(String avatar, String nickname, String signature) {
                    super();
                    this.avatar = avatar;
                    this.nickname = nickname;
                    this.signature = signature;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getSignature() {
                    return signature;
                }

                public void setSignature(String signature) {
                    this.signature = signature;
                }
            }
        }
    }
}
