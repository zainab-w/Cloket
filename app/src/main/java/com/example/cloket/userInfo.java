package com.example.cloket;

public class userInfo {

    public String FName;
    public String LName;
    public String phoneNum;

    public userInfo(String FName, String LName, String phoneNum)
    {
    }

    public String getFLame() {
        return FName;
    }

    public void setFLame(String FLame) {
        this.FName = FLame;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
