package com.wjh.domain;

import java.util.Date;

public class User {
    private String name;
    private String gender;
    private Float height;
    private Double pay;
    private Boolean vip;
    private Date birthday;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Float getHeight() {
        return height;
    }

    public Double getPay() {
        return pay;
    }

    public Boolean getVip() {
        return vip;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", pay=" + pay +
                ", vip=" + vip +
                ", birthday=" + birthday +
                '}';
    }
}
