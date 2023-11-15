package com.example.moviesbackend.config;

import com.example.moviesbackend.utils.Constants;
import com.example.moviesbackend.utils.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class BeanConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/**").permitAll())
                .csrf(token -> token.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));

        return httpSecurity.build();
    }

    @Bean
    LoggedUser loggedUser() {
        return new LoggedUser(Constants.DEFAULT_USERNAME, Constants.DEFAULT_EMAIL);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
