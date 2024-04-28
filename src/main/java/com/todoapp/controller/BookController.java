package com.todoapp.controller;

import com.todoapp.entity.Book;
import com.todoapp.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("books/create")
    public ResponseEntity<Book> create(@RequestParam("title") String title,
                                       @RequestParam("name") String name,
                                       @RequestParam("genre") String genre){
        log.info("Response call to /books/create");
        return ResponseEntity.ok(bookService.create(title, name, genre));
    }
    @GetMapping("books/getall")
    public ResponseEntity<List<Book>> getAll(){
        log.info("Response call to /books/getall");
        return ResponseEntity.ok(bookService.getAll());
    }
    @GetMapping("books/getbyid")
    public ResponseEntity<Optional<Book>> getBookById(@RequestParam("id") int id){
        log.info("Response call to /books/getbookbyid");
        return ResponseEntity.ok(bookService.getById(id));
    }
    @DeleteMapping("books/deletebyid")
    public void deleteBookById(int id){
        log.info("Response call to /books/deletebookbyid");
        bookService.deleteById(id);
    }
}
