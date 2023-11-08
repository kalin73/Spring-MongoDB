package com.example.moviesbackend.model.dto;

import com.example.moviesbackend.model.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewDto {
    private ObjectId id;
    private String username;
    private String body;

    public static ReviewDto mapToReviewDto(Review review) {
        return ReviewDto.builder()
                .body(review.getBody())
                .username(review.getUser().getUsername())
                .id(review.getId())
                .build();
    }
}
