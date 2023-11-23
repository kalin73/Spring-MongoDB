package com.example.moviesbackend.jwt;

import com.example.moviesbackend.model.dto.AuthenticatedUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {
    private final AuthenticatedUser principal;

    public UserPrincipalAuthenticationToken(AuthenticatedUser principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public AuthenticatedUser getPrincipal() {
        return principal;
    }
}
