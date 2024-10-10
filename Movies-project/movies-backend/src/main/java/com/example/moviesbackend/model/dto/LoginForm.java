package com.example.moviesbackend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    @Email
    private String email;

    @Size(min = 3, max = 20)
    private String password;
}
