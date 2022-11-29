package com.example.mall.entity;

import java.util.Date;

public class User {

    private Integer id;
    private String username;
    private String password;
    private String type;  // 0-管理员    1-买家    2-卖家
    private Integer account;  // 余额
    public User() {
    }

    public User(Integer id, String username, String password, String type, Integer account) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", account=" + account +
                '}';
    }
}
