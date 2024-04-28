package com.todoapp.service;

import com.todoapp.entity.Author;
import com.todoapp.entity.Book;
import com.todoapp.entity.enums.BookEnum;
import com.todoapp.repository.AuthorRepository;
import com.todoapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;

    public Book create(String title, String name, String genre){
        Author author = authorService.getAuthorByName(name);
        if(author == null){
            author = authorService.create(name);
        }
        Book book = new Book(title, author, BookEnum.valueOf(genre.toUpperCase()));
        return bookRepository.save(book);
    }

    public List<Book> getAll(){
        return (List<Book>) bookRepository.findAll();
    }

    public Optional<Book> getById(int id){
        return bookRepository.findById(id);
    }

    public void deleteById(int id){
        bookRepository.deleteById(id);
    }
}
