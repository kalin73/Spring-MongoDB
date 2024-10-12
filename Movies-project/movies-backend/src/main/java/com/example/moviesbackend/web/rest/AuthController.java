package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.LoginForm;
import com.example.moviesbackend.model.dto.RegisterForm;
import com.example.moviesbackend.model.dto.UserDto;
import com.example.moviesbackend.service.AuthService;
import com.example.moviesbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Validated RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .toList();

            return ResponseEntity.badRequest().body(errors);
        }

        return ResponseEntity.ok(this.userService.registerUser(registerForm));
    }

    @GetMapping
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        this.userService.verifyToken(token);

        return ResponseEntity.ok("Verified");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Validated LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

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
