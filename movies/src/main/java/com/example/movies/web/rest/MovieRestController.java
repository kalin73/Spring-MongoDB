package com.example.movies.web.rest;

import com.example.movies.model.entity.Movie;
import com.example.movies.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieRestController {
    private final MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<List<Movie>> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.created(URI.create("")).body(this.movieService.allMovies());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(this.movieService.allMovies());
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getMovie(@PathVariable("imdbId") String imdbId) {
        return ResponseEntity.ok(this.movieService.getMovieByImdbId(imdbId));
    }

}
