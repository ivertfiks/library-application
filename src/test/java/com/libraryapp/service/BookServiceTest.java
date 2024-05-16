package com.libraryapp.service;

import com.libraryapp.entity.Author;
import com.libraryapp.entity.Book;
import com.libraryapp.entity.enums.BookGenre;
import com.libraryapp.repository.BookRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Assertions;
@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookService bookService;

    @Test
    public void getAll_shouldReturnList(){
        List<Book> expectedList = Arrays.asList(new Book("Book1", new Author("Author1"), BookGenre.FANTASY));
        when(bookRepository.findAll()).thenReturn(expectedList);

        List<Book> actualList = bookService.getAll();
        Assertions.assertNotNull(actualList);
        Assertions.assertFalse(actualList.isEmpty());
        Assertions.assertEquals(1, actualList.size());
        Assertions.assertEquals("Book1", actualList.get(0).getTitle());
        verify(bookRepository).findAll();
    }
    @Test
    public void getById_shouldReturnBook(){
        Book expectedBook = new Book("Book", new Author("Author"), BookGenre.FANTASY);
        expectedBook.setId(1);
        when(bookRepository.findById(1)).thenReturn(Optional.of(expectedBook));

        Book actualBook = bookService.getById(1);
        Assertions.assertNotNull(actualBook);
        Assertions.assertEquals("Book", actualBook.getTitle());
        verify(bookRepository).findById(1);
    }
    @Test
    public void deleteById_shouldDeleteBook(){
        Book book = new Book("Book", new Author("Author"), BookGenre.FANTASY);
        book.setId(1);
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        bookService.deleteById(1);
        verify(bookRepository).deleteById(1);
        when(bookRepository.findById(1)).thenReturn(Optional.empty());
    }
    @Test
    public void createBook_shouldCreateBook(){
        Book expectedBook = new Book("Book", new Author("Author"), BookGenre.FANTASY);
        when(bookRepository.save(any(Book.class))).thenReturn(expectedBook);

        Book actualBook = bookService.create("Book", "Author", "FANTASY");

        Assertions.assertNotNull(actualBook);
        Assertions.assertEquals(expectedBook, actualBook);
        verify(bookRepository).save(any(Book.class));
    }
}
