package com.libraryapp.service;

import com.libraryapp.entity.Author;
import com.libraryapp.entity.Book;
import com.libraryapp.entity.enums.BookGenre;
import com.libraryapp.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public Book create(String title, String name, String genre) {
        Author author = authorService.getAuthorByName(name);
        if (author == null) {
            author = authorService.create(name);
        }
        Book book = new Book(title, author, BookGenre.valueOf(genre.toUpperCase()));
        return bookRepository.save(book);
    }

    public List<Book> getAll() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book getById(int id) {
        return bookRepository.findById(id).get();
    }

    public Book getByTitle(String title){
        return bookRepository.getBookByTitle(title);
    }

    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByAuthor(Author author){
        return bookRepository.getBooksByAuthor(author);
    }

    public List<Book> getBooksByGenre(BookGenre genre){
        return getBooksByGenre(genre);
    }
}
