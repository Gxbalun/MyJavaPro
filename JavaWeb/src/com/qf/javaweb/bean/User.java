package com.qf.javaweb.bean;

public class User {

    private String account;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return password;
    }

    public void setPass(String password) {
        this.password = password;
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }
}
