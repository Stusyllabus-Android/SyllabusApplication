package com.stu.syllabus.bean;

public class Login {
    /**
     * {
     *     "code": 200,
     *     "message": "success",
     *     "data": {
     *         "token": "153257"
     *     }
     * }
     */
    int code;
    String message;
    Data data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    static class Data{
        String token;
    }
}
