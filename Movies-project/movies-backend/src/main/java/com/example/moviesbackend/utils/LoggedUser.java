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
        this.clearFields();
    }

    public void clearFields() {
        this.username = "Anonymous";
        this.email = "anonymous@gmail.com";
    }
}
