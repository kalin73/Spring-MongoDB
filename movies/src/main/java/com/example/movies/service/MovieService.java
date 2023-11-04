package com.example.movies.service;

import com.example.movies.model.entity.Movie;
import com.example.movies.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> allMovies() {
        return this.movieRepository.findAll();
    }

    public void addMovie(Movie movie) {
        this.movieRepository.save(movie);
    }

    public Movie getMovieById(ObjectId id) {
        return this.movieRepository.findById(id).get();
    }
}
