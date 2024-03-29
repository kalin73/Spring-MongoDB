package com.example.moviesbackend.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private final String message;
    private final String accessToken;
}
