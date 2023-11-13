package com.example.moviesbackend.service;

import com.example.moviesbackend.repository.ConfirmationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationService {
    private final ConfirmationRepository confirmationRepository;
}
