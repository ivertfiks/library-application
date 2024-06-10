package com.libraryapp.controller;

import com.libraryapp.entity.Author;
import com.libraryapp.entity.Book;
import com.libraryapp.entity.enums.BookGenre;
import com.libraryapp.service.AuthorService;
import com.libraryapp.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<Book> create(@RequestParam("title") String title,
        @RequestParam("name") String name,
        @RequestParam("genre") String genre) {
        log.info("Response call to /books/create");
        return ResponseEntity.ok(bookService.create(title, name, genre));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("Response call to /books/list");
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/getBookById")
    public ResponseEntity<Book> getBookById(@RequestParam("id") int id) {
        log.info("Response call to /books/getBookById");
        return ResponseEntity.ok(bookService.getById(id));
    }

    @DeleteMapping("/deleteBookById")
    public ResponseEntity<?> deleteBookById(@RequestParam("id") int id) {
        log.info("Response call to /books/deleteBookById");
        Book bookToDelete = bookService.getById(id);
        if (bookToDelete != null) {
            bookService.deleteById(id);
            return ResponseEntity.ok(bookToDelete);
        } else {
            return new ResponseEntity<>("Cannot remove book as it doesn't exist: ",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBooksByAuthor")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam("name") String authorName) {
        Author author = authorService.getAuthorByName(authorName);
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

    @GetMapping("/getBooksByGenre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam("genre") String genre) {
        return ResponseEntity.ok(bookService.getBooksByGenre(BookGenre.valueOf(genre.toUpperCase())));
    }
}
