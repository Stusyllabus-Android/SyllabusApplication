package com.stu.syllabus.home.wirelessData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    private static Gson mGson = new GsonBuilder()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .create();

    public static Gson getDefault() {
        return mGson;
    }
}