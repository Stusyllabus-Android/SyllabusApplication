package com.stu.syllabus.person;

import android.util.Log;

import com.stu.syllabus.bean.ShowInfoBean;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * yuan
 * 2019/11/25
 **/
public class PersonPresenter implements PersonContract.presenter {

    private final String TAG = this.getClass().getSimpleName();

    PersonContract.view view;
    IPersonModel model;

    @Inject
    public PersonPresenter(PersonContract.view view, IPersonModel personModel) {
        super();
        this.view = view;
        this.model = personModel;
    }

    @Override
    public void init() {
        Log.d(TAG, "init: ");
        model.getUserInfoFromDisk()
                .subscribe(new Observer<ShowInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShowInfoBean showInfoBean) {
                        view.init(showInfoBean.getId(), showInfoBean.getAvatar(), showInfoBean.getNickname(), showInfoBean.getSignature(), showInfoBean.getCurrentSemester());
                        Log.d(TAG, "onNext: " + showInfoBean.getCurrentSemester());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updateCurrentSemester(String currentSemester) {
        model.updateCurrentSemester(currentSemester);
        init();
    }
}
