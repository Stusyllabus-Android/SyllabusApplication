package com.stu.syllabus.information;

import android.view.View;

import com.stu.syllabus.di.AuthRetrofit;
import com.stu.syllabus.di.PerFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * yuan
 * 2019/11/28
 **/
//@Module
//public class OAModule {
//    private OAContract.view view;
//    private int mPosition;
//    public OAModule(OAContract.view view, int position) {
//        super();
//        this.view = view;
//        mPosition = position;
//    }
//
//    @PerFragment
//    @Provides
//    int providePosition() {
//        return mPosition;
//    }
//
//
//    @PerFragment
//    @Provides
//    OAContract.view provideView() {
//        return view;
//    }
//}
@Module
public class OAModule {
    private OAContract.view view;

    public OAModule(OAContract.view view) {
        super();
        this.view = view;
    }

    @Provides
    OAContract.view provideView() {
        return view;
    }

    @Provides
    IOAModel provideModel(@AuthRetrofit Retrofit retrofit) {
        return new OAModel(retrofit);
    }
}