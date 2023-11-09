package com.example.moviesbackend.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoggedUser {
    private String username = Constants.DEFAULT_USERNAME;
    private String email = Constants.DEFAULT_EMAIL;

    public LoggedUser() {
        this.clearFields();
    }

    public void clearFields() {
        this.username = Constants.DEFAULT_USERNAME;
        this.email = Constants.DEFAULT_EMAIL;
    }
}
