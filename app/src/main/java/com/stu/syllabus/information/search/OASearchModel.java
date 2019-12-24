package com.stu.syllabus.information.search;

import com.stu.syllabus.bean.OAArticle;
import com.stu.syllabus.bean.OASearchBean;
import com.stu.syllabus.information.OASearch;
import com.stu.syllabus.retrofitApi.GetOAArticleAPi;
import com.stu.syllabus.retrofitApi.GetOASearch;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
/*@author cxy
 * by2019/12/24
 * */
public class OASearchModel implements IOASearchModel {
    GetOASearch getoasearch;

    public OASearchModel(Retrofit retrofit) {
        super();
        getoasearch = retrofit.create(GetOASearch.class);
    }
    @Override
    public Observable<List<OASearchBean>> getOASearch(String keyword,int row_start, int row_end) {
        return getoasearch.getOASearch(keyword, row_start, row_end)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
