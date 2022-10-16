package com.example.mall.entity;

import java.util.Date;

public class User {

    private int id;
    private String username;
    private String password;
    private String salt;
    private int type;  // 0-管理员    1-卖家    2-买家

    public User(int id, String username, String password, String salt, int type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", type=" + type +
                '}';
    }

}
