package com.libraryapp.controller;

import com.libraryapp.entity.Author;
import com.libraryapp.entity.Book;
import com.libraryapp.entity.enums.BookGenre;
import com.libraryapp.service.BookService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllBooks_shouldReturnBookList() throws Exception {
        // perform GET request to endpoint /books/list
        // make sure that OK (200) response is returned
        // and ResponseEntity status is okay
        List<Book> bookList = List.of(new Book(), new Book());
        when(bookService.getAll()).thenReturn(bookList);
        mockMvc.perform(get("/books/list")).andExpect(status().isOk());
    }

    @Test
    void createBook_shouldCreateBook() throws Exception {
        // create author
        Author author = new Author("J.K.Rowling");
        author.setId(1);
        // create book
        Book book = new Book("Harry Potter", author, BookGenre.FANTASY);
        book.setId(1);
        when(bookService.create("Harry Potter", "J.K.Rowling", BookGenre.FANTASY.toString())).thenReturn(book);
        mockMvc.perform(post("/books/create")
                        .param("title", "Harry Potter")
                        .param("name", "J.K.Rowling")
                        .param("genre", BookGenre.FANTASY.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getBookById_shouldReturnBook() throws Exception {
        Author author = new Author("J.K.Rowling");
        author.setId(1);
        Book book = new Book("Harry Potter", author, BookGenre.FANTASY);
        book.setId(1);
        int expectedId = 1;
        when(bookService.getById(expectedId)).thenReturn(book);
        mockMvc.perform(get("/books/getBookById")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBookById_shouldDeleteBook() throws Exception {
        Author author = new Author("J.K.Rowling");
        Book book = new Book("Harry Potter", author, BookGenre.FANTASY);
        book.setId(1);
        when(bookService.getById(1)).thenReturn(book);
        mockMvc.perform(delete("/books/deleteBookById")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertNotNull(bookService.getById(1));
    }

    @Test
    void deleteBookById_shouldNotDeleteBook() throws Exception {
        when(bookService.getById(1)).thenReturn(null);
        mockMvc.perform(delete("/books/deleteBookById")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
        Assertions.assertNull(bookService.getById(1));
    }
}
