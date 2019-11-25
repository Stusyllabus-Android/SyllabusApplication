package com.stu.syllabus.person;

import com.stu.syllabus.bean.ShowInfoBean;

import io.reactivex.Observable;

/**
 * yuan
 * 2019/11/25
 **/
public interface IPersonModel {
    Observable<ShowInfoBean> getUserInfoFromDisk();
}
