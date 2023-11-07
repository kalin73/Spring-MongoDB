package com.example.moviesbackend.service;

import com.example.moviesbackend.model.entity.Movie;
import com.example.moviesbackend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> allMovies() {
        return this.movieRepository.findAll();
    }

    public Optional<Movie> getMovieByImdbId(String imdbId) {
        return this.movieRepository.findMovieByImdbId(imdbId);
    }
}
