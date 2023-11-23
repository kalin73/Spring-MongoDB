package com.example.moviesbackend.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.moviesbackend.model.dto.AuthenticatedUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipalConverter {

    public AuthenticatedUser convert(DecodedJWT jwt) {
        return AuthenticatedUser.builder()
                .id(jwt.getSubject())
                .email(jwt.getClaim("e").asString())
                .authorities(extractAuthoritiesFromClaim(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt) {
        Claim claim = jwt.getClaim("au");

        if (claim.isNull() || claim.isMissing()) return List.of();

        return claim.asList(SimpleGrantedAuthority.class);
    }
}
