package com.example.moviesbackend.service;

import com.example.moviesbackend.model.dto.RegisterForm;
import com.example.moviesbackend.model.entity.User;
import com.example.moviesbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public String registerUser(RegisterForm registerForm) {
        if (this.userRepository.findUserByEmail(registerForm.getEmail()).isPresent()) {
            return "exists";
        }

        User user = new User();
        user.setEmail(registerForm.getEmail());
        user.setUsername(registerForm.getUsername());
        user.setPassword((passwordEncoder.encode(registerForm.getPassword())));

        this.userRepository.save(user);

        return "notexist";
    }
}
