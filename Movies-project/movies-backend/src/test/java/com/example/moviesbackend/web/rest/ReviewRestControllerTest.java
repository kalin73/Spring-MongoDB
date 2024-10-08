package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.ReviewDto;
import com.example.moviesbackend.service.ReviewService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReviewRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        when(reviewService.createReview(anyString(), anyString())).thenReturn(new ReviewDto(ObjectId.get(), "Pesho", "Good movie!"));
    }

    @Test
    public void testCreateReview() throws Exception {
        mockMvc.perform(post("/api/v1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "reviewBody": "Good movie!",
                          "imdbId": "qwe"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.body").value("Good movie!"));
    }

    @Test
    public void testCreateReviewWithNoReviewBody() throws Exception {
        mockMvc.perform(post("/api/v1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "reviewBody": "",
                          "imdbId": "qwe"
                        }
                        """))
                .andExpect(status().isBadRequest());
    }
}
