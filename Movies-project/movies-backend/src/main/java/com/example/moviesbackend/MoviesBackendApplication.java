package com.example.moviesbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MoviesBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesBackendApplication.class, args);
    }

}
