package com.libraryapp.controller;

import com.libraryapp.entity.Author;
import com.libraryapp.service.AuthorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllAuthors_shouldReturnAuthorList() throws Exception {
        List<Author> authorList = List.of(new Author(), new Author());
        when(authorService.getAll()).thenReturn(authorList);
        mockMvc.perform(get("/authors/list")).andExpect(status().isOk());
    }

    @Test
    void createAuthor_shouldCreateAuthor() throws Exception {
        Author author = new Author("J.K.Rowling");
        author.setId(1);
        when(authorService.create("J.K.Rowling")).thenReturn(author);
        mockMvc.perform(post("/authors/create")
                        .param("name", "J.K.Rowling")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAuthorById_shouldReturnAuthor() throws Exception {
        Author author = new Author("J.K.Rowling");
        author.setId(1);
        int expectedId = 1;
        when(authorService.getById(expectedId)).thenReturn(author);
        mockMvc.perform(get("/authors/getAuthorById")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void deleteAuthorById_shouldDeleteAuthor() throws Exception {
        Author author = new Author();
        author.setId(1);
        when(authorService.getById(1)).thenReturn(author);
        mockMvc.perform(delete("/authors/deleteAuthorById")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    @Test
    void deleteAuthorById_shouldNotDeleteAuthor() throws Exception {
        when(authorService.getById(1)).thenReturn(null);
        mockMvc.perform(delete("/authors/deleteAuthorById")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

}