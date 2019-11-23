package com.stu.syllabus.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * yuan
 * 2019/11/12
 **/
public class YiBanTimeTable {
    @SerializedName("Table")
    public List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public static class TableBean {
        /**
         * xnxq_name : 2017-2018学年秋季学期
         * kkb_key : 95421
         * kc_name : [ELC1]英语(ELC1)
         * js_name : 苏雪枫
         * ks_name : D座307
         * sj_name : 第1-16周，周二(12节)，周五(34节)
         */

        @SerializedName("xnxq_name")
        public String xnxqName;
        @SerializedName("kkb_key")
        public int kkbKey;
        @SerializedName("kc_name")
        public String kcName;
        @SerializedName("js_name")
        public String jsName;
        @SerializedName("ks_name")
        public String ksName;
        @SerializedName("sj_name")
        public String sjName;

        public String getXnxqName() {
            return xnxqName;
        }

        public void setXnxqName(String xnxqName) {
            this.xnxqName = xnxqName;
        }

        public int getKkbKey() {
            return kkbKey;
        }

        public void setKkbKey(int kkbKey) {
            this.kkbKey = kkbKey;
        }

        public String getKcName() {
            return kcName;
        }

        public void setKcName(String kcName) {
            this.kcName = kcName;
        }

        public String getJsName() {
            return jsName;
        }

        public void setJsName(String jsName) {
            this.jsName = jsName;
        }

        public String getKsName() {
            return ksName;
        }

        public void setKsName(String ksName) {
            this.ksName = ksName;
        }

        public String getSjName() {
            return sjName;
        }

        public void setSjName(String sjName) {
            this.sjName = sjName;
        }
    }
}
