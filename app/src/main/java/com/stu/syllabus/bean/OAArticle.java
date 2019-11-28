package com.stu.syllabus.bean;

import com.google.gson.annotations.SerializedName;

/**
 * yuan
 * 2019/11/28
 **/
public class OAArticle {
    /**
     * title : CKAD艺术与设计大讲堂：汉字生活
     * publishDate : 2019-11-28 15:24:12
     * department : 长江艺术与设计学院
     * id : 20255
     */

    @SerializedName("title")
    public String title;
    @SerializedName("publishDate")
    public String publishDate;
    @SerializedName("department")
    public String department;
    @SerializedName("id")
    public int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
