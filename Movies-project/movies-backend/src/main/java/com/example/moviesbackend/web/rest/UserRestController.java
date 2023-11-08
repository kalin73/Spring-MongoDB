package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.UserDto;
import com.example.moviesbackend.service.UserService;
import com.example.moviesbackend.utils.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loggedUser")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserRestController {
    private final LoggedUser loggedUser;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getLoggedUser() {
        return ResponseEntity.ok(this.userService.getUserByEmail(loggedUser.getEmail()));
    }
}
