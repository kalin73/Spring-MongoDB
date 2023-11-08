package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.RegisterForm;
import com.example.moviesbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterForm registerForm) {
        return ResponseEntity.ok(this.userService.registerUser(registerForm));
    }
}
