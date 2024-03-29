package com.example.moviesbackend.service;

import com.example.moviesbackend.model.dto.AuthenticatedUser;
import com.example.moviesbackend.model.dto.LoginResponse;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;

    public AuthService(JwtService jwtService, AuthenticationProvider authenticationProvider) {
        this.jwtService = jwtService;
        this.authenticationProvider = authenticationProvider;
    }

    public LoginResponse login(String email, String password) {
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthenticatedUser principal = (AuthenticatedUser) authentication.getPrincipal();

        List<String> roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        String token = jwtService.generateToken(principal);

        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }
}
