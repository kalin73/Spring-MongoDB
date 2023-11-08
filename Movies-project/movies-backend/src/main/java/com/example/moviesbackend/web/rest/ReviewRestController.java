package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.ReviewDto;
import com.example.moviesbackend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/reviews")
@CrossOrigin(origins = "*")
public class ReviewRestController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> create(@RequestBody Map<String, String> payload) {
        return ResponseEntity.created(URI.create("")).body(this.reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")));
    }
}
