package com.stu.syllabus.util;

import com.stu.syllabus.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AssetUtil {
    public static String getStringFromPath(String path) {
        StringBuffer fileData = new StringBuffer();
        try {
            InputStreamReader isr = new InputStreamReader(
                    App.getContext().getResources().getAssets().open(path), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            String Result = "";

            while ((line = br.readLine()) != null)
                Result += line;
            return Result;
        } catch (IOException e) {
            return "";
        }
    }
}
