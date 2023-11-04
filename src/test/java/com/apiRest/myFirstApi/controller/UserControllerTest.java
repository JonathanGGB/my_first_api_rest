package com.apiRest.myFirstApi.controller;

import com.apiRest.myFirstApi.entity.User;
import com.apiRest.myFirstApi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    private User user;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void configureUser(){
        user = new User();
    }

    @Test
    public void getSuccess() throws Exception {
        Long userId = 1L;
        user.setId(userId);
        given(userService.getUser(userId)).willReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetTaskNotFound() throws Exception {
        Long userId = (long) -1;
        given(userService.getUser(userId)).willReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
