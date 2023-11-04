package com.example.movies.web.rest;

import com.example.movies.model.entity.Review;
import com.example.movies.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/reviews")
public class ReviewRestController {
    private final ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        return ResponseEntity.created(URI.create("")).body(this.reviewService.addReview(review));
    }
}
