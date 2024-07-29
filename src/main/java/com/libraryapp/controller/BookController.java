package com.libraryapp.controller;

import com.libraryapp.entity.Author;
import com.libraryapp.entity.Book;
import com.libraryapp.entity.enums.BookGenre;
import com.libraryapp.service.AuthorService;
import com.libraryapp.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @PostMapping("/create")
    public String create(@RequestParam("title") String title,
                         @RequestParam("name") String name,
                         @RequestParam("genre") String genre,
                         Model model) {
        log.info("Response call to /books/create");
        Book createdBook = bookService.create(title, name, genre);
        model.addAttribute("book", createdBook);
        return "index";
    }

    @GetMapping("/list")
    public String getAllBooks(Model model) {
        log.info("Response call to /books/list");
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/getBookById")
    public String getBookById(@RequestParam("id") int id, Model model) {
        log.info("Response call to /books/getBookById");
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "index";
    }

    @DeleteMapping("/deleteBookById")
    public String deleteBookById(@RequestParam("id") int id, Model model) {
        log.info("Response call to /books/deleteBookById");
        Book bookToDelete = bookService.getById(id);
        if (bookToDelete != null) {
            bookService.deleteById(id);
            model.addAttribute("message", "Book deleted successfully");
        } else {
            model.addAttribute("message", "Cannot remove book as it doesn't exist");
        }
        return "index";
    }

    @GetMapping("/getBooksByAuthor")
    public String getBooksByAuthor(@RequestParam("name") String authorName, Model model) {
        Author author = authorService.getAuthorByName(authorName);
        List<Book> books = bookService.getBooksByAuthor(author);
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/getBooksByGenre")
    public String getBooksByGenre(@RequestParam("genre") String genre, Model model) {
        List<Book> books = bookService.getBooksByGenre(BookGenre.valueOf(genre.toUpperCase()));
        model.addAttribute("books", books);
        return "index";
    }
}
