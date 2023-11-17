package com.example.moviesbackend.config;

import com.example.moviesbackend.repository.UserRepository;
import com.example.moviesbackend.service.AuthenticatedUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/api/**", "/auth/login", "/").permitAll())
                .formLogin(login -> login.loginPage("/auth/login")
                        .defaultSuccessUrl("/api/v1/movies")
                        .failureForwardUrl("/login-error"))
                .cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000/")
                        .allowedMethods("*")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    UserDetailsService userDetails(UserRepository userRepository) {
        return new AuthenticatedUserService(userRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
