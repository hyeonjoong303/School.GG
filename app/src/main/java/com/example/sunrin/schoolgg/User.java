package com.example.sunrin.schoolgg;

public class User {
    String name;
    String schoolnumber;
    String nickname;
    String tier;
    int ranking;
    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolnumber() {
        return schoolnumber;
    }

    public void setSchoolnumber(String schoolnumber) {
        this.schoolnumber = schoolnumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public User(String name, String schoolnumber, String nickname, String tier) {
        this.name = name;
        this.schoolnumber = schoolnumber;
        this.nickname = nickname;
        this.tier = tier;
    }
}
