package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.LoginForm;
import com.example.moviesbackend.model.dto.RegisterForm;
import com.example.moviesbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterForm registerForm) {
        return ResponseEntity.ok(this.userService.registerUser(registerForm));
    }

    @GetMapping
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        this.userService.verifyToken(token);

        return ResponseEntity.ok("Verified");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(),
                loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("exists");
    }

    @PostMapping("/login-error")
    public String loginError() {
        return "notexists";
    }

    @GetMapping("/logoutSuccess")
    public ResponseEntity<String> successfulLogout() {
        return ResponseEntity.ok("Logged out!");
    }
}
