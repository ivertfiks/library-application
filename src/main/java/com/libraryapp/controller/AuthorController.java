package com.libraryapp.controller;

import com.libraryapp.entity.Author;
import com.libraryapp.service.AuthorService;
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
public class AuthorController{
    @Autowired
    private AuthorService authorService;

    @PostMapping("/authors/create")
    public ResponseEntity<Author> create(@RequestParam("name") String name){
        log.info("Response call to authors/create");
        return ResponseEntity.ok(authorService.getAuthorByName(name));
    }
    @GetMapping("/authors/list")
    public ResponseEntity<List<Author>> getAll(){
        return ResponseEntity.ok(authorService.getAll());
    }
    @GetMapping("/authors/getAuthorById")
    public ResponseEntity<Optional<Author>> getAuthorById(@RequestParam("id") int id){
        return ResponseEntity.ok(authorService.getById(id));
    }
    @DeleteMapping("/authors/deleteAuthorById")
    public void deleteById(@RequestParam("id") int id){
        authorService.deleteById(id);
    }
}
