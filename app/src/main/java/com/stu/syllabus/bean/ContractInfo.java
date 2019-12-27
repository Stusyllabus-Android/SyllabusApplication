package com.stu.syllabus.bean;

import com.stu.syllabus.R;

public class ContractInfo {
    private int headImage;
    private String nickname;
    private String postTime;
    private String postDevice;
    private String content;
    private int zans;
    private int comments;

    public ContractInfo(String nickname, String postTime, String content, int zans, int comments){
        this.comments = comments;
        this.content = content;
        this.nickname = nickname;
        this.postTime = postTime;
        this.zans = zans;
        setHeadImage(R.drawable.bg_headimage);
        setPostDevice("匿名");
    }

    public int getHeadImage() {
        return headImage;
    }

    public void setHeadImage(int headImage) {
        this.headImage = headImage;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostDevice() {
        return postDevice;
    }

    public void setPostDevice(String postDevice) {
        this.postDevice = postDevice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getZans() {
        return zans;
    }

    public void setZans(int zans) {
        this.zans = zans;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
