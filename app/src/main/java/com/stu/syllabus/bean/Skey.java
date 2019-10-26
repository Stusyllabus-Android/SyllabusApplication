package com.stu.syllabus.bean;

/**
 * yuan
 * 2019/10/23
 **/
public class Skey {
    /**
     * {
     *     "skey": "4840d26a-f82e-4be1-b3a6-7956f6ffdccb",
     *     "skeyExpiresAt": "2019-10-29 10:09:42",
     *     "refresh_key": "9d8a3abb-ee0e-461d-a586-d5e3e75ea80d",
     *     "refreshKeyExpiresAt": "2019-11-25 10:09:42",
     *     "code": "0"
     * }
     */
    String skey;
    String skeyExpiresAt;
    String refresh_key;
    String refreshKeyExpiresAt;
    String code;

    public String getSkey() {
        return skey;
    }

    public String getRefresh_key() {
        return refresh_key;
    }
}
