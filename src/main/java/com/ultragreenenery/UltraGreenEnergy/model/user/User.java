package com.ultragreenenery.UltraGreenEnergy.model.user;

import jakarta.validation.constraints.NotBlank;

public class User {


    @NotBlank(message = "Login name is required")
    private  String loginName;
    @NotBlank(message = "Password is required")
    private  String password;
    @NotBlank(message = "Email id is required")
    private String emailId;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Mobile Number is required")
    private String mobileNo;

    public User(){}
    public User(String loginName, String password, String emailId, String name, String mobileNo){
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
