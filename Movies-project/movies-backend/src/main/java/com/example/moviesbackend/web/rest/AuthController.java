package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.LoginForm;
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

    @GetMapping
    public ResponseEntity<String> verifyEmail(@RequestParam("token")String token){
        this.userService.verifyToken(token);

        return ResponseEntity.ok("Verified");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(this.userService.loginUser(loginForm));
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        this.userService.logOut();

        return ResponseEntity.ok("");
    }
}
