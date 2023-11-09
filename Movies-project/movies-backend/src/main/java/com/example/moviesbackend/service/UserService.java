package com.example.moviesbackend.service;

import com.example.moviesbackend.model.dto.LoginForm;
import com.example.moviesbackend.model.dto.RegisterForm;
import com.example.moviesbackend.model.dto.UserDto;
import com.example.moviesbackend.model.entity.User;
import com.example.moviesbackend.repository.UserRepository;
import com.example.moviesbackend.utils.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final LoggedUser loggedUser;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

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

    public String loginUser(LoginForm loginForm) {
        Optional<User> user = this.userRepository.findUserByEmail(loginForm.getEmail());
        boolean passwordMatch = user.filter(value -> passwordEncoder.matches(loginForm.getPassword(), value.getPassword())).isPresent();

        if (passwordMatch) {
            loggedUser.setEmail(user.get().getEmail());
            loggedUser.setUsername(user.get().getUsername());

            return "exists";
        }

        return "notexist";
    }

    public void logOut() {
        loggedUser.clearFields();
    }

    public UserDto getLoggedUser() {
        return UserDto.mapToUserDto(loggedUser);
    }
}
