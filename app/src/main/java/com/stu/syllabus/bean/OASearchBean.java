package com.stu.syllabus.bean;
/*@author cxy
 * by2019/12/24
 * */
public class OASearchBean {
    private String title;
    public String publishDate;
    private String department;
    private int id;

    public OASearchBean() {
//        keyword = "";
        id = 0;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String  getTitle(){
        return title;
    }
    public void setTitle(String title){
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
}
