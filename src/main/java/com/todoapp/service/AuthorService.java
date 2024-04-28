package com.todoapp.service;

import com.todoapp.entity.Author;
import com.todoapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author create(String name){
        Author author = new Author(name);
        return authorRepository.save(author);
    }

    public Author getAuthorByName(String name){
        return authorRepository.getAuthorByName(name);
    }

    public List<Author> getAll(){
        return (List<Author>) authorRepository.findAll();
    }

    public void deleteById(int id){
        authorRepository.deleteById(id);
    }

    public Optional<Author> getById(int id){
        return authorRepository.findById(id);
    }
}
