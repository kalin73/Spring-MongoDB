package com.example.moviesbackend;

import com.example.moviesbackend.model.entity.Movie;
import com.example.moviesbackend.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private MovieRepository movieRepository;

    public ConsoleRunner(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Movie movie = new Movie();
        movie.setImdbId("qwe");
        movie.setTitle("Fast and Furious 8");
        movie.setReleaseDate("14.04.2017");
        movie.setTrailerLink("https://www.youtube.com/watch?v=uisBaTkQAEs&ab_channel=UniversalPicturesUK");
        movie.setPoster("./image/FF8.jpg");
        movie.setGenres(List.of("Action"));
        movie.setBackdrops(List.of());


        this.movieRepository.save(movie);
    }
}