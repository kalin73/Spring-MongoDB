package com.example.movies.service;

import com.example.movies.model.entity.Movie;
import com.example.movies.model.entity.Review;
import com.example.movies.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
        Review review = this.reviewRepository.save(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }
}
