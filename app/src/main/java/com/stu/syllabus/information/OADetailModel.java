//package com.stu.syllabus.information;
//
//import com.stu.syllabus.bean.OADetail;
//
//import com.stu.syllabus.retrofitApi.GetOADetailApi;
//
//import retrofit2.Call;
//import retrofit2.Retrofit;
//
//public class OADetailModel implements IOADetailModel{
//    GetOADetailApi getOADetailAPi;
//
//
//    public OADetailModel(Retrofit retrofit) {
//        super();
//        getOADetailAPi = retrofit.create(GetOADetailApi.class);
//    }
//
//
//    public Call<OADetail> getOADetail(String id) {
//
//        return getOADetailAPi.getOADetail(id);
//    }
//}
