package com.libraryapp.service;

import com.libraryapp.entity.Author;
import com.libraryapp.entity.Book;
import com.libraryapp.entity.enums.BookGenre;
import com.libraryapp.repository.BookRepository;
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
        Book book = new Book(title, author, BookGenre.valueOf(genre.toUpperCase()));
        return bookRepository.save(book);
    }

    public List<Book> getAll(){
        return (List<Book>) bookRepository.findAll();
    }

    public Book getById(int id){
        return bookRepository.findById(id).get();
    }

    public void deleteById(int id){
        bookRepository.deleteById(id);
    }
}
