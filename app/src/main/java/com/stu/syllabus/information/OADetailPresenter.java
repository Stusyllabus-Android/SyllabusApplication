//package com.stu.syllabus.information;
//
//import android.util.Log;
//
//import com.stu.syllabus.bean.OADetail;
//
//import javax.inject.Inject;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class OADetailPresenter implements OADetailContract.presenter {
//    OADetailContract.view view;
//    IOADetailModel model;
//    String id;
//    @Inject
//    public OADetailPresenter(OADetailContract.view view, IOADetailModel model) {
//        super();
//        this.view = view;
//        this.model = model;
//    }
//    @Override
//    public void init() {
//        Call<OADetail> oadetail=model.getOADetail(id);
//       oadetail .enqueue(new Callback<OADetail>() {
//                @Override
//                public void onResponse(Call<OADetail> call, Response<OADetail> response) {
//                    try{
////                        Thread.sleep(1000);
//                        OADetail OAdetail = response.body();
////                        OAdetail.getContent();
//                        String message=OAdetail.title;
//                        Log.i("text",message);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<OADetail> call, Throwable t) {
//                    Log.i("text","fail");
//                }
//        });
//}
//}