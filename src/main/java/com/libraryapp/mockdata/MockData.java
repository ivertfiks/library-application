package com.libraryapp.mockdata;

import com.libraryapp.entity.exceptions.DuplicateUserException;
import com.libraryapp.service.AuthorService;
import com.libraryapp.service.BookService;
import com.libraryapp.service.impl.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MockData {

    // create variables for userService, bookService, authorService
    private final UserServiceImpl userServiceImpl;
    private final BookService bookService;
    private final AuthorService authorService;

    @PostConstruct
    public void createInitialData(){
        // create User
        try {
            userServiceImpl.createUser("username", "pass", "name");
        } catch (DuplicateUserException e) {
            throw new RuntimeException(e);
        }
        // Create Author
        authorService.create("Patrick Rothfuss");
        // Create books
        bookService.create("The Name of the Wind", "Patrick Rothfuss", "Fantasy");
        bookService.create("The Slow Regard of Silent Things", "Patrick Rothfuss", "Fantasy");
        // add books for author
        // add books in some user lists
        userServiceImpl.addReadingBook("username", "The Name of the Wind");
        userServiceImpl.addReadingBook("username", "The Slow Regard of Silent Things");
    }

}
