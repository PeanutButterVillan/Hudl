package com.gvs.framework.model;


//This page is under future implementation

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class AdminLogInInfo {

    private String username;
    private String password;
    private String regEmailID;

    public AdminLogInInfo() {
    }

    public AdminLogInInfo(String uid, String pwd) {
        this.username = uid;
        this.password = pwd;
    }

    public AdminLogInInfo(String uid, String pwd, String regEmail) {
        this.username = uid;
        this.password = pwd;
        this.regEmailID = regEmail;
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

    public String getRegEmailID() {
        return regEmailID;
    }

    public void setRegEmailID(String regEmailID) {
        this.regEmailID = regEmailID;
    }

}
