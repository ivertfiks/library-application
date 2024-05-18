package com.libraryapp.service;

import com.libraryapp.entity.Author;
import com.libraryapp.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void getAll_shouldReturnList(){
        List<Author> expectedList = Arrays.asList(new Author("Author"));
        when(authorRepository.findAll()).thenReturn(expectedList);

        List<Author> actualList = authorService.getAll();
        Assertions.assertNotNull(actualList);
        Assertions.assertFalse(actualList.isEmpty());
        Assertions.assertEquals(1, actualList.size());
        Assertions.assertEquals("Author", actualList.get(0).getName());
        verify(authorRepository).findAll();
    }
    @Test
    public void getById_shouldReturnAuthor(){
        Author expectedAuthor = new Author("Author");
        expectedAuthor.setId(1);
        when(authorRepository.findById(1)).thenReturn(Optional.of(expectedAuthor));

        Author actualAuthor = authorService.getById(1);
        Assertions.assertNotNull(actualAuthor);
        Assertions.assertEquals("Author", actualAuthor.getName());
        verify(authorRepository).findById(1);
    }
    @Test
    public void deleteById_shouldDeleteAuthor(){
        Author author = new Author("Author");
        author.setId(1);
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        authorService.deleteById(1);
        verify(authorRepository).deleteById(1);
        when(authorRepository.findById(1)).thenReturn(Optional.empty());
    }
    @Test
    public void createAuthor_shouldCreateAuthor(){
        Author expectedAuthor = new Author("Author");
        when(authorRepository.save(any(Author.class))).thenReturn(expectedAuthor);

        Author actualAuthor = authorService.create("Author");

        Assertions.assertNotNull(actualAuthor);
        Assertions.assertEquals(expectedAuthor, actualAuthor);
        verify(authorRepository).save(any(Author.class));
    }
}
