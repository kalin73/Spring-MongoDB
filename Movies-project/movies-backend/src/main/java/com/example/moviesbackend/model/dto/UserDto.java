package com.example.moviesbackend.model.dto;

import com.example.moviesbackend.utils.LoggedUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String username;
    private String email;
    private boolean isLogged;

    public static UserDto mapToUserDto(LoggedUser loggedUser) {
        boolean logged = !loggedUser.getUsername().equals("Anonymous");

        return UserDto.builder()
                .email(loggedUser.getEmail())
                .username(loggedUser.getUsername())
                .isLogged(logged)
                .build();
    }
}
