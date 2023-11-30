package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.UserDto;
import com.example.moviesbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loggedUser")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getLoggedUser() {
        UserDto user = this.userService.getLoggedUser();

        return ResponseEntity.ok(user);
    }
}
