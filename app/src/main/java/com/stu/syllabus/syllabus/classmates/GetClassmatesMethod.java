package com.stu.syllabus.syllabus.classmates;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.stu.syllabus.bean.ClassmateInfo;
import com.stu.syllabus.retrofitApi.GetClassmatesInfoApi;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * yuan
 * 2019/12/26
 **/
public class GetClassmatesMethod {

    private static final GetClassmatesMethod ourInstance = new GetClassmatesMethod();

    public static GetClassmatesMethod getInstance() {
        return ourInstance;
    }

    private GetClassmatesMethod() {
    }

    public Observable<String> getClassmatesInfo(long classID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://credit.stu.edu.cn/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        GetClassmatesInfoApi getClassmatesInfoApi = retrofit.create(GetClassmatesInfoApi.class);

        return getClassmatesInfoApi.getClassmatesInfo(classID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
