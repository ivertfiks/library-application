package com.libraryapp.service.impl;

import com.libraryapp.entity.Book;
import com.libraryapp.entity.User;
import com.libraryapp.entity.exceptions.DuplicateUserException;
import com.libraryapp.repository.BookRepository;
import com.libraryapp.repository.UserRepository;
import com.libraryapp.service.BookService;
import com.libraryapp.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final BCryptPasswordEncoder encoder;

    public User createUser(String username, String password, String name) throws DuplicateUserException {
        User user = userRepository.getUserByUsername(username);
        if(user == null) {
            return userRepository.save(new User(name, username, encoder.encode(password)));
        }
        throw new DuplicateUserException("Duplicate user: " + username + "\nYou should choose another username...");
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    public void deleteUserByUsername(String username){
        userRepository.deleteUserByUsername(username);
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).get();
    }

    @Transactional
    public User addReadingBook(String username, String bookTitle){
        User user = userRepository.getUserByUsername(username);
        List<Book> readingBooks = user.getReadingBooks();
        readingBooks.add(bookService.getByTitle(bookTitle));
        user.setReadingBooks(readingBooks);
        return userRepository.save(user);
    }

    public void updatePasswordByUsername(String username, String newPassword){
        User user = userRepository.getUserByUsername(username);
        String encodedPassword = encoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public void addBookToWantToRead(int userId, int bookId) {
        User user = getUserById(userId);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        user.getWantToReadBooks().add(book);
        userRepository.save(user);
    }

    public void addBookToReading(int userId, int bookId) {
        User user = getUserById(userId);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        user.getReadingBooks().add(book);
        userRepository.save(user);
    }

    public void addBookToFinished(int userId, int bookId) {
        User user = getUserById(userId);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        user.getFinishedBooks().add(book);
        userRepository.save(user);
    }

}
