package com.example.moviesbackend.web.rest;

import com.example.moviesbackend.model.dto.UserDto;
import com.example.moviesbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthControllerTest {
    private static final String REGISTER_URL = "/api/v1/auth/register";
    private static final String LOGIN_URL = "/api/v1/auth/login";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {
        when(userService.registerUser(any())).thenReturn("registered");
        when(userService.loginUser(any())).thenReturn(new UserDto("kalin", "kalin@abv.bg", true));
        when(userService.logOut()).thenReturn(new UserDto("Anonymous", "anonymous@abv.bg", false));
        doNothing().when(userService).verifyToken(any());
    }

    @Test
    public void testRegisterUser() throws Exception {
        mockMvc.perform(post(REGISTER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "kalin",
                                    "email":"kalin@abv.bg",
                                    "password": "123"
                                }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterUserWithEmptyUsername() throws Exception {
        mockMvc.perform(post(REGISTER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "",
                                    "email":"kalin@abv.bg",
                                    "password": "123"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUserLogin() throws Exception {
        mockMvc.perform(post(LOGIN_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "email":"kalin@abv.bg",
                                    "password":"123"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("kalin"))
                .andExpect(jsonPath("$.email").value("kalin@abv.bg"));
    }

    @Test
    public void testUserLoginWrongEmail() throws Exception {
        mockMvc.perform(post(LOGIN_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "email":"kalinabv.bg",
                                    "password":"123"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Invalid username or password"));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/api/v1/auth/logout")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Anonymous"))
                .andExpect(jsonPath("$.email").value("anonymous@abv.bg"));
    }

    @Test
    public void testVerifyToken() throws Exception {
        mockMvc.perform(get("/api/v1/auth")
                        .param("token", "token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Verified"));
    }
}
