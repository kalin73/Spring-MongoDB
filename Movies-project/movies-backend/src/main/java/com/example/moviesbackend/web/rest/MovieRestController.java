package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.MovieDto;
import com.example.moviesbackend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "*")
public class MovieRestController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> list = this.movieService.allMovies();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable("imdbId") String imdbId) {
        return ResponseEntity.ok(this.movieService.getMovieByImdbId(imdbId));
    }

}
