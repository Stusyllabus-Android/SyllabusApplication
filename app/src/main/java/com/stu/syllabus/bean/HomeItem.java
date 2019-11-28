package com.stu.syllabus.bean;


import java.util.List;

public class HomeItem {
    private String func;
    private List<HomeItemsItem> funcItem;

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public List<HomeItemsItem> getFuncItem() {
        return funcItem;
    }

    public void setFuncItem(List<HomeItemsItem> funcItem) {
        this.funcItem = funcItem;
    }
}
