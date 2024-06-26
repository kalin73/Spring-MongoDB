package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.LoginForm;
import com.example.moviesbackend.model.dto.RegisterForm;
import com.example.moviesbackend.model.dto.UserDto;
import com.example.moviesbackend.service.AuthService;
import com.example.moviesbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

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
    public ResponseEntity<UserDto> login(@RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(this.userService.loginUser(loginForm));
    }

    @GetMapping("/logout")
    public ResponseEntity<UserDto> logout() {
        return ResponseEntity.ok(this.userService.logOut());
    }


//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginForm loginForm, HttpServletResponse httpResponse) {
//        LoginResponse response = this.authService.login(loginForm.getEmail(), loginForm.getPassword());
//
//        httpResponse.addHeader("Authorization", response.getAccessToken());
//
//        return ResponseEntity.ok(response.getMessage());
//    }

    @PostMapping("/login-error")
    public String loginError() {
        return "notexists";
    }

    @GetMapping("/logoutSuccess")
    public ResponseEntity<String> successfulLogout() {
        return ResponseEntity.ok("Logged out!");
    }


//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
//        try {
//            request.login(loginForm.getEmail(), loginForm.getPassword());
//
//        } catch (ServletException e) {
//            return new ResponseEntity<>("notexists", HttpStatusCode.valueOf(401));
//
//        }
//
//        return ResponseEntity.ok("exists");
//    }
}
