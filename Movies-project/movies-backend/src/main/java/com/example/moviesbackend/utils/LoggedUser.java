package com.example.moviesbackend.utils;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoggedUser {
    private String username;
    private String email;

    public void clearFields(){
        this.username = null;
        this.email = null;
    }
}
