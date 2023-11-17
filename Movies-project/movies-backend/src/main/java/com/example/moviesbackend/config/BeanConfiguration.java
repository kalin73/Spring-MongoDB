package com.example.moviesbackend.config;

import com.example.moviesbackend.utils.Constants;
import com.example.moviesbackend.utils.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    LoggedUser loggedUser() {
        return new LoggedUser(Constants.DEFAULT_USERNAME, Constants.DEFAULT_EMAIL);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
