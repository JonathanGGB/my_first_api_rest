package com.apiRest.myFirstApi.controller;

import com.apiRest.myFirstApi.entity.User;
import com.apiRest.myFirstApi.repository.UserRepository;
import com.apiRest.myFirstApi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    private User user;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

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
    public void getIdNotFound() throws Exception {
        Long userId = (long) -1;
        given(userService.getUser(userId)).willReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getSuccessUserByEmail() throws Exception {
        String email = "jonathangogbz@gmail.com";
        user.setEmail(email);
        assertEquals("Email: ", email, user.getEmail());
        when(userRepository.findByEmail(email)).thenReturn(user);
        given(userService.findByEmail(email)).willReturn(user);
        userService.findByEmail(email);
        verify(userService).findByEmail(email);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/findByEmail?email=", email))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateSuccess() throws Exception {
        user.setId(5L);
        user.setName("Jon");
        user.setEmail("jonathan@gmail.com");
        when(userService.updateUser(user.getId(), user)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        System.out.println(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Jonathan\", \"email\": \"jonathangogbz@gmail.com\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteSuccess() throws Exception {
        user.setId(5L);
        when(userService.deleteUser(user.getId())).thenReturn(true);
        System.out.println(user);
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteFailed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", 4))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
