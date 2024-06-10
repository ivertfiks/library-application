package com.libraryapp.controller;

import com.libraryapp.entity.Author;
import com.libraryapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<Author> create(@RequestParam("name") String name) {
        log.info("Response call to /authors/create");
        return ResponseEntity.ok(authorService.getAuthorByName(name));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Author>> getAllAuthors() {
        log.info("Response call to /authors/list");
        return ResponseEntity.ok(authorService.getAll());
    }

    @GetMapping("/getAuthorById")
    public ResponseEntity<Author> getAuthorById(@RequestParam("id") int id) {
        log.info("Response call to /authors/getAuthorById");
        return ResponseEntity.ok(authorService.getById(id));
    }

    @DeleteMapping("/deleteAuthorById")
    public ResponseEntity<?> deleteAuthorById(@RequestParam("id") int id) {
        log.info("Response call to /authors/deleteAuthorById");
        Author authorToDelete = authorService.getById(id);
        if (authorToDelete != null) {
            authorService.deleteById(id);
            return ResponseEntity.ok(authorToDelete);
        } else {
            return new ResponseEntity<>("Cannot remove author as it doesn't exist: ", HttpStatus.BAD_REQUEST);
        }
    }
}
