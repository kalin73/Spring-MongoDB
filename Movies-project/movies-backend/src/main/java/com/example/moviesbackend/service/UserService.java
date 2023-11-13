package com.example.moviesbackend.service;

import com.example.moviesbackend.model.dto.LoginForm;
import com.example.moviesbackend.model.dto.RegisterForm;
import com.example.moviesbackend.model.dto.UserDto;
import com.example.moviesbackend.model.entity.Confirmation;
import com.example.moviesbackend.model.entity.Movie;
import com.example.moviesbackend.model.entity.User;
import com.example.moviesbackend.repository.ConfirmationRepository;
import com.example.moviesbackend.repository.UserRepository;
import com.example.moviesbackend.utils.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ConfirmationRepository confirmationRepository;

    private final EmailService emailService;

    private final MongoTemplate mongoTemplate;

    private final LoggedUser loggedUser;


    public String registerUser(RegisterForm registerForm) {
        if (this.userRepository.findUserByEmail(registerForm.getEmail()).isPresent()) {
            return "exists";
        }

        User user = new User();
        user.setEmail(registerForm.getEmail());
        user.setUsername(registerForm.getUsername());
        user.setPassword((passwordEncoder.encode(registerForm.getPassword())));
        user.setEnabled(false);

        user = this.userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmation = confirmationRepository.save(confirmation);

        emailService.sendMessage(user.getUsername(), user.getEmail(), confirmation.getToken());

        return "notexist";
    }

    public void verifyToken(String token) {
        Confirmation confirmation = this.confirmationRepository.findConfirmationByToken(token);
        String userEmail = confirmation.getUser().getEmail();

        mongoTemplate.update(User.class)
                .matching(Criteria.where("email").is(userEmail))
                .apply(new Update().push("isEnabled").value(true))
                .first();

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
