package com.ultragreenenery.UltraGreenEnergy.entity.user;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "user")
public class User {


    private  String loginName;
    private  String password;
    private String emailId;
    private String name;
    private String mobileNo;
    private  String  loginStatus;
    private Date registerDate;

    public User(){}
    public User(String loginName,String password,String emailId,String name,String mobileNo){
        this.loginName=loginName;
        this.password=password;
        this.emailId=emailId;
        this.name=name;
        this.mobileNo=mobileNo;

    }



    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getLoginStatus() {return loginStatus;}

    public void setLoginStatus(String loginStatus) {  this.loginStatus = loginStatus; }

    public Date getRegisterDate() {  return registerDate; }

    public void setRegisterDate(Date registerDate) { this.registerDate = registerDate; }

    @Override
    public String toString() {
        return "User{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", emailId='" + emailId + '\'' +
                ", name='" + name + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
