package com.example.moviesbackend.service;

import com.example.moviesbackend.jwt.JwtIssuer;
import com.example.moviesbackend.model.dto.AuthenticatedUser;
import com.example.moviesbackend.model.dto.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    public AuthService(JwtIssuer jwtIssuer, AuthenticationManager authenticationManager) {
        this.jwtIssuer = jwtIssuer;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthenticatedUser principal = (AuthenticatedUser) authentication.getPrincipal();

        List<String> roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        String token = jwtIssuer.issue(principal.getId(), principal.getEmail(), roles);

        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }
}
