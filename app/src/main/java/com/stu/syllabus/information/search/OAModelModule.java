package com.stu.syllabus.information;

import com.stu.syllabus.bean.OASearchBean;
import com.stu.syllabus.di.AuthRetrofit;
import com.stu.syllabus.di.OASearchRetrofit;
import com.stu.syllabus.di.PerModule;
import com.stu.syllabus.information.search.IOASearchModel;
import com.stu.syllabus.information.search.OASearchContract;
import com.stu.syllabus.information.search.OASearchModel;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
/*@author cxy
 * by2019/12/24
 * */
@Module
public  class OAModelModule {
    private OASearchContract.view view;

    public OAModelModule(OASearchContract.view view) {
        super();
        this.view = view;
    }

    @Provides
    OASearchContract.view provideView() {
        return view;
    }
    @Provides
    IOASearchModel provideModel(@OASearchRetrofit Retrofit retrofit) {
        return new OASearchModel(retrofit);
    }
}
