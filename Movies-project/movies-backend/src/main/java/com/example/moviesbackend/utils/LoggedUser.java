package com.example.moviesbackend.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoggedUser {
    private String username;
    private String email;


    public void clearFields() {
        this.username = Constants.DEFAULT_USERNAME;
        this.email = Constants.DEFAULT_EMAIL;
    }
}
