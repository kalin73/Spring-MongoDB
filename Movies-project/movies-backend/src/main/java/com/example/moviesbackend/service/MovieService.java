package com.example.moviesbackend.service;

import com.example.moviesbackend.model.dto.MovieDto;
import com.example.moviesbackend.model.entity.Movie;
import com.example.moviesbackend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public List<Movie> allMovies() {
        return this.movieRepository.findAll();
    }

    public MovieDto getMovieByImdbId(String imdbId) {
        return MovieDto.mapToMovieDto(this.movieRepository.findMovieByImdbId(imdbId).get());
    }
}
