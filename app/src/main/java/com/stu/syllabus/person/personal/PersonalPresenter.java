package com.stu.syllabus.person.personal;

import android.util.Log;

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

    private final String TAG = this.getClass().getSimpleName();

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
        // TODO: 2020/2/6 改一下更新接口为三个参数
        Log.d(TAG, "updateUserInfo: " + avatar + " " + nickname + " " + signature);
        if (avatar != null) {
            personalModel.updateAvatar(avatar)
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
                            Log.d(TAG, "onError: ");
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

        personalModel.saveUserInfoToDisk(avatar, nickname, signature);
    }

}
