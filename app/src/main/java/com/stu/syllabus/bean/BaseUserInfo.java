package com.stu.syllabus.bean;

/**
 * yuan
 * 2019/11/13
 **/
public class BaseUserInfo {
    String account;

    String password;

    public BaseUserInfo(String account, String password) {
        super();
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "BaseUserInfo{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
