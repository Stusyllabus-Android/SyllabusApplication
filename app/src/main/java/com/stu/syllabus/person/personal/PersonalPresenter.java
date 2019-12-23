package com.stu.syllabus.person.personal;

import com.stu.syllabus.bean.ShowInfoBean;
import com.stu.syllabus.person.PersonContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * yuan
 * 2019/12/23
 **/
public class PersonalPresenter implements PersonContract.presenter {

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
}
