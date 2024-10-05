package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.MovieDto;
import com.example.moviesbackend.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieRestControllerTest {
    private final String baseURI = "/api/v1/movies";

    @MockBean
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MovieDto movie1 = new MovieDto();
        movie1.setImdbId("qwe");
        movie1.setTitle("Fast and Furious 8");
        movie1.setReleaseDate("14.04.2017");
        movie1.setTrailerLink("https://www.youtube.com/watch?v=uisBaTkQAEs&ab_channel=UniversalPicturesUK");
        movie1.setPoster("./image/FF8.jpg");
        movie1.setGenres(List.of("Action"));
        movie1.setBackdrops(List.of());

        MovieDto movie2 = new MovieDto();
        movie2.setImdbId("ghj");
        movie2.setTitle("Deadpool 2");
        movie2.setReleaseDate("14.04.2019");
        movie2.setTrailerLink("https://www.youtube.com/watch?v=uisBaTkQAEs&ab_channel=UniversalPicturesUK");
        movie2.setPoster("./image/FF8.jpg");
        movie2.setGenres(List.of("Action"));
        movie2.setBackdrops(List.of());

        when(movieService.allMovies()).thenReturn(List.of(movie1, movie2));
        when(movieService.getMovieByImdbId("ghj")).thenReturn(movie2);

    }

    @Test
    public void getAllMovies() throws Exception {
        mockMvc.perform(get(baseURI)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].imdbId").value("qwe"))
                .andExpect(jsonPath("$.[1].imdbId").value("ghj"))
                .andExpect(jsonPath("$.[0].title").value("Fast and Furious 8"))
                .andExpect(jsonPath("$.[1].title").value("Deadpool 2"));
    }

    @Test
    public void getMovieById() throws Exception {
        mockMvc.perform(get(baseURI + "/{imdbId}", "ghj")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Deadpool 2"));
    }
}
