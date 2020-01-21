package com.stu.syllabus.person.personal;

import com.stu.syllabus.bean.PostUserInfoResult;
import com.stu.syllabus.bean.ShowInfoBean;
import com.stu.syllabus.person.PersonContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * yuan
 * 2019/12/23
 **/
public class PersonalPresenter implements PersonalContract.presenter {

    PersonalContract.view view;
    IPersonalModel personalModel;

    @Inject
    public PersonalPresenter(PersonalContract.view view, IPersonalModel personalModel) {
        super();
        this.view = view;
        this.personalModel = personalModel;
    }

    @Override
    public void init() {
        personalModel.getUserInfoFromDisk()
                .subscribe(new Observer<ShowInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShowInfoBean showInfoBean) {
                        view.loadInfo(showInfoBean.getAvatar(), showInfoBean.getId(), showInfoBean.getNickname(), showInfoBean.getSignature());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateUserInfo(String avatar, String nickname, String signature) {
        if (avatar != null) {
            // TODO: 2020/1/21 更新头像
        }
        if (nickname != null) {
            personalModel.updateNickname(nickname)
                    .subscribe(new Observer<PostUserInfoResult>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(PostUserInfoResult postUserInfoResult) {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

        if (signature != null) {
            personalModel.updateSignature(signature)
                    .subscribe(new Observer<PostUserInfoResult>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(PostUserInfoResult postUserInfoResult) {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

}
