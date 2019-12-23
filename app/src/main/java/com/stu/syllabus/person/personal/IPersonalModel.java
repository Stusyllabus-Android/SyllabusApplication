package com.stu.syllabus.person.personal;

import com.stu.syllabus.bean.ShowInfoBean;

import io.reactivex.Observable;

/**
 * yuan
 * 2019/12/23
 **/
public interface IPersonalModel {

    Observable<ShowInfoBean> getUserInfoFromDisk();

    Observable<String> updateAvatar();

    Observable<String> updateNickname();

    Observable<String> updateSignature();
}
