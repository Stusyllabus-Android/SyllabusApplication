//package com.stu.syllabus.information;
//
//import com.stu.syllabus.di.AuthRetrofit;
//import com.stu.syllabus.di.OADetailRetrofit;
//
//import dagger.Module;
//import dagger.Provides;
//import retrofit2.Retrofit;
//@Module
//public class OADetailModule {
//    private OADetailContract.view view;
//
//    public OADetailModule(OADetailContract.view view) {
//        super();
//        this.view = view;
//    }
//
//    @Provides
//    OADetailContract.view provideView() {
//        return view;
//    }
//
//    @Provides
//    IOADetailModel provideModel(@OADetailRetrofit Retrofit retrofit) {
//        return new OADetailModel(retrofit);
//    }
//}
