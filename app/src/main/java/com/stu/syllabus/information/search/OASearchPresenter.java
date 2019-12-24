package com.stu.syllabus.information.search;

import android.util.Log;

import com.stu.syllabus.bean.OASearchBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
/*@author cxy
 * by2019/12/24
 * */
public class OASearchPresenter {
    private String TAG = this.getClass().getSimpleName();

    OASearchContract.view view;
    IOASearchModel model;

    @Inject
    public OASearchPresenter(OASearchContract.view view, IOASearchModel model) {
        super();
        this.view = view;
        this.model = model;
    }


    public void init(String keyword) {
        model.getOASearch(keyword, 0, 30)
                .subscribe(new Observer<List<OASearchBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSub " );
                    }

                    @Override
                    public void onNext(List<OASearchBean> oasearchbean) {
                        Log.d(TAG, "onNext: " + oasearchbean.size());
                        view.showOASearch(oasearchbean);
                        view.isRefresh(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "o: " );
                    }
                });
    }
}
