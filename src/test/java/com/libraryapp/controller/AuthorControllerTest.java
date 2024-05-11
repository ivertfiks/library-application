package com.libraryapp.controller;

import com.libraryapp.entity.Author;
import com.libraryapp.service.AuthorService;
import org.junit.jupiter.api.Assertions;
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
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllAuthors_shouldReturnAuthorList() throws Exception {
        List<Author> authorList = List.of(new Author(), new Author());
        when(authorService.getAll()).thenReturn(authorList);
        mockMvc.perform(get("/authors/list")).andExpect(status().isOk());
    }

    @Test
    public void createAuthor_shouldCreateAuthor() throws Exception {
        Author author = new Author("J.K.Rowling");
        author.setId(1);
        when(authorService.create("J.K.Rowling")).thenReturn(author);
        mockMvc.perform(post("/authors/create")
                        .param("name", "J.K.Rowling")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAuthorById_shouldReturnAuthor() throws Exception {
        Author author = new Author("J.K.Rowling");
        author.setId(1);
        int expectedId = 1;
        when(authorService.getById(expectedId)).thenReturn(Optional.of(author));
        mockMvc.perform(get("/authors/getAuthorById")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAuthorById_shouldDeleteAuthor() throws Exception {
        Author author = new Author("J.K.Rowling");
        authorService.create("J.K.Rowling");
        Assertions.assertNotNull(authorService.getById(1));
        mockMvc.perform(delete("/authors/deleteAuthorById")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertNull(authorService.getById(1));
    }

}
