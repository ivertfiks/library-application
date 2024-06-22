package com.libraryapp.controller;

import com.libraryapp.entity.User;
import com.libraryapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createUser_shouldCreateUser() throws Exception {
        User user = new User("name", "username", "password");
        user.setId(1);
        when(userService.createUser("username", "password", "name")).thenReturn(user);
        mockMvc.perform(post("/users/register")
                .param("username", "name")
                .param("password", "password")
                .param("name", "name")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "username", roles = {"User"})
    void getUserByUsername_shouldReturnUserByUsername() throws Exception {
        User user = new User("name", "username", "password");
        user.setId(1);
        when(userService.getUserByUsername("username")).thenReturn(user);
        mockMvc.perform(get("/users/getUserByUsername")
                .param("username", "username")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "username", roles = {"User"})
    void deleteUserByUsername_shouldDeleteUserByUsername() throws Exception {
        User user = new User("user", "username", "password");
        when(userService.getUserByUsername("username")).thenReturn(user);
        mockMvc.perform(delete("/users/deleteUserByUsername")
                .param("username", "username")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "username", roles = {"User"})
    void deleteUserByUsername_shouldNotDeleteUserByUsername() throws Exception {
        mockMvc.perform(delete("/users/deleteUserByUsername")
                        .param("username", "username")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(username = "username", roles = {"User"})
    void getAllUsers_shouldReturnAllUsers() throws Exception {
        List<User> userList = List.of(new User(), new User());
        when(userService.getAllUsers()).thenReturn(userList);
        mockMvc.perform(get("/users/getAllUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "username", roles = {"User"})
    void getUserById_shouldReturnUsersById() throws Exception {
        User user = new User();
        user.setId(1);
        when(userService.getUserById(1)).thenReturn(user);
        mockMvc.perform(get("/users/getUserById")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "username", roles = {"User"})
    void updatePasswordByUsername_shouldUpdatePasswordByUsername() throws Exception {
        mockMvc.perform(post("/users/updatePasswordByUsername")
                .param("username", "username")
                .param("newPassword", "pass")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
