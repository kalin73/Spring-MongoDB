package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.UserDto;
import com.example.moviesbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    private static final String DEFAULT_URL = "/api/v1/loggedUser";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        when(userService.getLoggedUser()).thenReturn(new UserDto("Test", "test@abv.bg", true));
    }

    @Test
    public void testGetLoggedUser() throws Exception {
        mockMvc.perform(get(DEFAULT_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Test"))
                .andExpect(jsonPath("$.email").value("test@abv.bg"))
                .andExpect(jsonPath("$.logged").value(true));
    }
}
