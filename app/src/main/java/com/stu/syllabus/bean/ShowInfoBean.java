package com.stu.syllabus.bean;

/**
 * yuan
 * 2019/11/25
 **/
public class ShowInfoBean {
    String id;
    String avatar;
    String nickname;
    String signature;
    String currentSemester;

    public ShowInfoBean(String id, String avatar, String nickname, String signature, String currentSemester) {
        super();
        this.id = id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.signature = signature;
        this.currentSemester = currentSemester;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
    }
}
