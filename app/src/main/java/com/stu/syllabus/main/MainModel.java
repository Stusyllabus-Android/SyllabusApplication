package com.stu.syllabus.main;

import com.stu.syllabus.DataBaseHelper;

import retrofit2.Retrofit;

/**
 * yuan
 * 2019/10/26
 **/
public class MainModel implements IMainModel{

    public DataBaseHelper dataBaseHelper;
    public Retrofit authRetrofit;
    public Retrofit businessRetrofit;


    public MainModel(DataBaseHelper dataBaseHelper, Retrofit authRetrofit, Retrofit businessRetrofit) {
        super();
        this.dataBaseHelper = dataBaseHelper;
        this.authRetrofit = authRetrofit;
        this.businessRetrofit = businessRetrofit;

    }

}
