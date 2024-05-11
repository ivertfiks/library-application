package com.libraryapp.service;

import com.libraryapp.entity.Author;
import com.libraryapp.entity.Book;
import com.libraryapp.entity.enums.BookGenre;
import com.libraryapp.repository.BookRepository;
import java.util.Arrays;
import java.util.List;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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




}
