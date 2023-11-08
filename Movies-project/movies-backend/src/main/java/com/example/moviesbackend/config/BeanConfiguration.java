package com.example.moviesbackend.config;

import com.example.moviesbackend.utils.LoggedUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class BeanConfiguration {
    @Bean
    @SessionScope
    LoggedUser loggedUser(){
        return new LoggedUser();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
