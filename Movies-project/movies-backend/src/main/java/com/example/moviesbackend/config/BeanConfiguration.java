package com.example.moviesbackend.config;

import com.example.moviesbackend.repository.UserRepository;
import com.example.moviesbackend.service.AuthenticatedUserService;
import com.example.moviesbackend.utils.Constants;
import com.example.moviesbackend.utils.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BeanConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/**").permitAll())
                .csrf(token -> token.disable());

        return httpSecurity.build();
    }

    @Bean
    UserDetailsService userDetails(UserRepository userRepository) {
        return new AuthenticatedUserService(userRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    LoggedUser loggedUser() {
        return new LoggedUser(Constants.DEFAULT_USERNAME, Constants.DEFAULT_EMAIL);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
