package com.stu.syllabus.information;


import com.stu.syllabus.bean.OADetail;

import retrofit2.Call;

public interface IOADetailModel {
    Call<OADetail> getOADetail(String id);
}
