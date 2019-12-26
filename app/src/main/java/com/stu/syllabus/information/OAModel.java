package com.stu.syllabus.information;

import com.stu.syllabus.bean.OAArticle;
import com.stu.syllabus.bean.OASearchBean;
import com.stu.syllabus.retrofitApi.GetOAArticleAPi;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * yuan
 * 2019/11/28
 **/
public class OAModel implements IOAModel {

    GetOAArticleAPi getOAArticleAPi;

    public OAModel(Retrofit retrofit) {
        super();
        getOAArticleAPi = retrofit.create(GetOAArticleAPi.class);
    }

    @Override
    public Observable<List<OAArticle>> getOAArticle(int row_start, int row_end, int subcompany_id) {
        return getOAArticleAPi.getOAArticle(row_start, row_end, subcompany_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
