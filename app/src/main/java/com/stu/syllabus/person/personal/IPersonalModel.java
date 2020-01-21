package com.stu.syllabus.person.personal;

import com.stu.syllabus.bean.PostUserInfoResult;
import com.stu.syllabus.bean.ShowInfoBean;

import io.reactivex.Observable;

/**
 * yuan
 * 2019/12/23
 **/
public interface IPersonalModel {

    Observable<ShowInfoBean> getUserInfoFromDisk();

    Observable<PostUserInfoResult> updateAvatar(String avatar);

    Observable<PostUserInfoResult> updateNickname(String nickname);

    Observable<PostUserInfoResult> updateSignature(String signature);
}
