package com.ultragreenenery.UltraGreenEnergy.secure;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))){

            response.sendRedirect("/admin/dashboard");
        }else{

            response.sendRedirect("/user/dashboard");
        }
    }
}
