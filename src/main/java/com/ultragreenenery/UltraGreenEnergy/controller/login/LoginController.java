package com.ultragreenenery.UltraGreenEnergy.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(){
        System.out.println("request to get login page...");
        return "login";
    }
    @GetMapping("/login-processing")
    public String loginApplication(){
        return "home";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }
}
