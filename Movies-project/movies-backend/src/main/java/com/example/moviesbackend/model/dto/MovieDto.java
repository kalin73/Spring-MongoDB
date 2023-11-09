package com.example.moviesbackend.model.dto;

import com.example.moviesbackend.model.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieDto {
    private String imdbId;

    private String title;

    private String releaseDate;

    private String trailerLink;

    private String poster;

    private List<String> genres;

    private List<String> backdrops;

    private List<ReviewDto> reviews;

    public static MovieDto mapToMovieDto(Movie movie){
        return MovieDto.builder()
                .imdbId(movie.getImdbId())
                .title(movie.getTitle())
                .releaseDate(movie.getReleaseDate())
                .trailerLink(movie.getTrailerLink())
                .poster(movie.getPoster())
                .genres(movie.getGenres())
                .backdrops(movie.getBackdrops())
                .reviews(movie.getReviews().stream().map(ReviewDto::mapToReviewDto).toList())
                .build();

    }
}
