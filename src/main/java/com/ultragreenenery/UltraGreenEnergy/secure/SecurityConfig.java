package com.ultragreenenery.UltraGreenEnergy.secure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)//disable csrf
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login-processing")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                        .defaultSuccessUrl("/dashboard")  // If the login is successful, user will be redirected to this URL.
                        .failureUrl("/login?error=true") // If the user fails to login, application will redirect the user to this endpoint
                ) //for form based login
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/WEB-INF/views/**", "/img/**", "/favicon.ico").permitAll() //  We are permitting all static resources to be accessed publicly
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN") 	// We are restricting endpoints for individual roles.// Only users with allowed roles will be able to access individual endpoints.
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()) //authenticate all requests

                //.httpBasic(Customizer.withDefaults()) // for basic authenication like when calling from postman
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login"))
                .build();
        // ..anyRequest().authenticated().formLogin(form -> form
        //.loginPage("/login").permitAll()).build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("userPass"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }

    //    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService)
//            throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.debug(true).ignoring().requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encoding
    }
}
