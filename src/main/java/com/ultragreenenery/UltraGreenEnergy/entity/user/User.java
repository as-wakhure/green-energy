package com.ultragreenenery.UltraGreenEnergy.entity.user;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Document(collection = "user")
public class User implements UserDetails {
    private  String username;
    private  String password;
    private String emailId;
    private String name;
    private String mobileNo;
    private  String  loginStatus;
    private LocalDate registerDate;
    private boolean enabled;

    public User(){}
    public User(String username,
                String password,
                String emailId, String name,
                String mobileNo){
        this.username=username;
        this.password=password;
        this.emailId=emailId;
        this.name=name;
        this.mobileNo=mobileNo;

    }

    public User(String username, String password, String emailId, String name, String mobileNo, String loginStatus,
                LocalDate registerDate, boolean enabled) {
        this.username = username;
        this.password = password;
        this.emailId = emailId;
        this.name = name;
        this.mobileNo = mobileNo;
        this.loginStatus = loginStatus;
        this.registerDate = registerDate;
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority=new SimpleGrantedAuthority("ROLE_USER");
        return List.of(authority);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public LocalDate getRegisterDate() {  return registerDate; }

    public void setRegisterDate(LocalDate registerDate) { this.registerDate = registerDate; }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", emailId='" + emailId + '\'' +
                ", name='" + name + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
