package com.libraryapp.service;

import com.libraryapp.entity.Author;
import com.libraryapp.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

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

    public Author getById(int id){
        return authorRepository.findById(id).get();
    }
}
