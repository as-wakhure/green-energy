package com.ultragreenenery.UltraGreenEnergy.secure;

import com.ultragreenenery.UltraGreenEnergy.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final CustomUserDetailService customUserDetailService;
//
//    public SecurityConfig(CustomUserDetailService customUserDetailService) {
//        this.customUserDetailService = customUserDetailService;
//    }
private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)//disable csrf temporary
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform-login")
                        .defaultSuccessUrl("/home",true)
                        .failureUrl("/login?error=true") // If the user fails to login, application will redirect the user to this endpoint
                        .permitAll()
                ) //for form based login
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/WEB-INF/views/**", "/img/**", "/favicon.ico", "/home").permitAll() //  We are permitting all static resources to be accessed publicly
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")    // We are restricting endpoints for individual roles.// Only users with allowed roles will be able to access individual endpoints.
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()) //authenticate all requests
                .oauth2Login(oauth-> oauth
                        .loginPage("/login")
                        .defaultSuccessUrl("/home",true))
                .httpBasic(Customizer.withDefaults()) // for basic authentication like when calling from postman
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .userDetailsService(userService);

        return http.build();
    }

    @Bean
    public HttpFirewall allowSemicolonHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true); // Allow semicolons in URL
        return firewall;
    }

//InMemory user details for POC
//@Bean
//public UserDetailsService userDetailsService() {
//    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//    manager.createUser(User.withUsername("user")
//            .password(passwordEncoder().encode("userPass"))
//            .roles("USER")
//            .build());
//    manager.createUser(User.withUsername("admin")
//            .password(passwordEncoder().encode("adminPass"))
//            .roles("USER", "ADMIN")
//            .build());
//    return manager;
//}
    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)//disable csrf
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/dashboard", true)
//                        .permitAll()) //for form based login
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // permit login and static resources
//                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//                        //        .requestMatchers("/public/**").permitAll()
//                        .anyRequest().authenticated()) //authenticate all requests
//
//                .httpBasic(Customizer.withDefaults()) // for basic authenication like when calling from postman
//                //.logout(LogoutConfigurer::permitAll)
//                .build();
//        // ..anyRequest().authenticated().formLogin(form -> form
//        //.loginPage("/login").permitAll()).build();
//    }
}
