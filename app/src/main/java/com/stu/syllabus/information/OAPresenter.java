package com.stu.syllabus.information;

import android.util.Log;

import com.stu.syllabus.bean.OAArticle;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * yuan
 * 2019/11/28
 **/
public class OAPresenter implements OAContract.presenter {
    private String TAG = this.getClass().getSimpleName();

    OAContract.view view;
    IOAModel model;

    @Inject
    public OAPresenter(OAContract.view view, IOAModel model) {
        super();
        this.view = view;
        this.model = model;
    }

    @Override
    public void init() {
        model.getOAArticle(0, 100, 0)
                .subscribe(new Observer<List<OAArticle>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSub " );
                    }

                    @Override
                    public void onNext(List<OAArticle> oaArticles) {
                        Log.d(TAG, "onNext: " + oaArticles.size());
                        view.showOA(oaArticles);
                        view.isRefresh(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
