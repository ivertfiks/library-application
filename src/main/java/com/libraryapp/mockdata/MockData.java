package com.libraryapp.mockdata;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MockData {

    // create variables for userService, bookService, authorService

    @PostConstruct
    public void createInitialData(){
        // create User
        // Create Author
        // Create books
        // add books for author
        // add books in some user lists
    }

}
