package com.example.food.Model;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String user;
    private String pass;
    private String name;
    private String phone;

    public UserModel() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserModel(String user, String pass, String name, String phone) {
        this.user = user;
        this.pass = pass;
        this.name = name;
        this.phone = phone;
    }
}
