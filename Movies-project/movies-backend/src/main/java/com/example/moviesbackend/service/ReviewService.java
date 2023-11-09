package com.example.moviesbackend.service;

import com.example.moviesbackend.model.dto.ReviewDto;
import com.example.moviesbackend.model.entity.Movie;
import com.example.moviesbackend.model.entity.Review;
import com.example.moviesbackend.model.entity.User;
import com.example.moviesbackend.repository.ReviewRepository;
import com.example.moviesbackend.repository.UserRepository;

import static com.example.moviesbackend.utils.Constants.*;

import com.example.moviesbackend.utils.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final MongoTemplate mongoTemplate;

    public ReviewDto createReview(String reviewBody, String imdbId) {
        User user;
        user = userRepository.findUserByEmail(loggedUser.getEmail()).get();

        Review review = new Review(reviewBody, user);
        review = this.reviewRepository.save(review);

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviews").value(review))
                .first();

        return ReviewDto.mapToReviewDto(review);
    }
}
