package com.example.moviesbackend.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
    private final JwtProperties jwtProperties;

    public String issue(String id, String email, List<String> roles) {
        Instant now = Instant.now();

        return JWT.create()
                .withSubject(String.valueOf(id))
                .withIssuedAt(now)
                .withExpiresAt(now.plus(3, ChronoUnit.MINUTES))
                .withClaim("e", email)
                .withClaim("au", roles)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }

}
