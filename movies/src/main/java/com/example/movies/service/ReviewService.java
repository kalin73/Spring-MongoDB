package com.example.movies.service;

import com.example.movies.model.entity.Review;
import com.example.movies.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        return this.reviewRepository.save(review);
    }
}
