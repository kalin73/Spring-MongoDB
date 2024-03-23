package com.example.moviesbackend.service;

import com.example.moviesbackend.model.dto.MovieDto;
import com.example.moviesbackend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Cacheable(cacheNames = "movies")
    public List<MovieDto> allMovies() {
        return this.movieRepository.findAll().stream().map(MovieDto::mapToMovieDto).toList();
    }

    @CacheEvict(cacheNames = "movies", allEntries = true)
    public void refreshMovies() {

    }

    public MovieDto getMovieByImdbId(String imdbId) {
        return this.movieRepository.findMovieByImdbId(imdbId)
                .map(MovieDto::mapToMovieDto)
                .orElse(null);
    }
}
