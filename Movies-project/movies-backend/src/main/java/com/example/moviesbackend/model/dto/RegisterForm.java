package com.example.moviesbackend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterForm {
    @Size(min = 3, max = 20)
    private String username;

    @Email
    private String email;

    @Size(min = 3, max = 20)
    private String password;
}
