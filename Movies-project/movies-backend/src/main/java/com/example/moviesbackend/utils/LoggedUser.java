package com.example.moviesbackend.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoggedUser {
    private String username;
    private String email;

    public LoggedUser() {
        this.email = "anonymous@gmail.com";
    }

    public void clearFields() {
        this.username = null;
        this.email = "anonymous@gmail.com";
    }
}
