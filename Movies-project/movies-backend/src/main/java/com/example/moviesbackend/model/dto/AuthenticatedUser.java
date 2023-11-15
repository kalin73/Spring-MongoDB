package com.example.moviesbackend.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthenticatedUser extends User {
    private String username;
    public AuthenticatedUser(String email, String password, Collection<? extends GrantedAuthority> authorities, String username) {
        super(email, password, authorities);
        this.username = username;
    }
}
