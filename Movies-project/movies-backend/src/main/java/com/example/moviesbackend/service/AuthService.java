package com.example.moviesbackend.service;

import com.example.moviesbackend.model.dto.AuthenticatedUser;
import com.example.moviesbackend.model.dto.LoginResponse;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;

    public AuthService(JwtService jwtService, AuthenticationProvider authenticationProvider) {
        this.jwtService = jwtService;
        this.authenticationProvider = authenticationProvider;
    }

    public LoginResponse login(String email, String password) {
        String loginStatusMessage;
        Authentication authentication = null;

        try {
            authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));

            loginStatusMessage = "Success";

        } catch (AuthenticationException ex) {
            loginStatusMessage = "Fail";
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        assert authentication != null;
        AuthenticatedUser principal = (AuthenticatedUser) authentication.getPrincipal();

        String token = jwtService.generateToken(principal);

        return LoginResponse.builder()
                .accessToken(token)
                .message(loginStatusMessage)
                .build();
    }
}
