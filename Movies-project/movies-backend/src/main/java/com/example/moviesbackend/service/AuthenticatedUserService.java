package com.example.moviesbackend.service;

import com.example.moviesbackend.model.dto.AuthenticatedUser;
import com.example.moviesbackend.model.entity.User;
import com.example.moviesbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userEntity = this.userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return AuthenticatedUser.builder()
                .id(userEntity.getId().toString())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_")))
                .username(userEntity.getUsername())
                .build();
    }
}
