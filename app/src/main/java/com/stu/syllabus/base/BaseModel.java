package com.stu.syllabus.base;

/**
 * yuan
 * 2019/10/23
 **/
public interface BaseModel {
    interface OnGetSuccessCallBack<T> {
        void onGetSuccess(T t);
    }
    interface OnGetFailCallBack<T> {
        void onGetFail();
    }
}
