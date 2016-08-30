package com.example.ienning.ncuhome.net;

/**
 * Created by ienning on 16-7-27.
 */
public class Jsondb {
    private String token;
    //学号
    private String xh;
    //姓名
    private String xm;
    //专业班级
    private String bjmc;
    //手机号码 手机号码不是Json格式
    private String phoneNumber;

    private UserScores scores;

    private String msg;

    private String terms;

    private int total;

    private int status;
    public String getToken() {
        return token;
    }

    public String getXh() {
        return xh;
    }

    public String getXm() {
        return xm;
    }

    public String getBjmc() {
        return bjmc;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public void setBjmc(String bjmc) {
        this.bjmc = bjmc;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserScores getScores() {
        return scores;
    }

    public void setScores(UserScores scores) {
        this.scores = scores;
    }
}
