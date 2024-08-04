package com.libraryapp.controller;

import com.libraryapp.entity.User;
import com.libraryapp.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/getUserByUsername")
    public ResponseEntity<User> getUserByUsername(@RequestParam("username") String username){
        return ResponseEntity.ok(userServiceImpl.getUserByUsername(username));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUserByUsername(@RequestParam("username") String username){
        User userToDelete = userServiceImpl.getUserByUsername(username);
        if(userToDelete != null){
            userServiceImpl.deleteUserByUsername(username);
            return ResponseEntity.ok(userToDelete);
        }else{
            return new ResponseEntity<>("Cannot remove user as it doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/list")
    public String getAllUsers(Model model) {
        List<User> users = userServiceImpl.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }
    @GetMapping("/get")
    public ResponseEntity<User> getUserById(@RequestParam("id") int id){
        return ResponseEntity.ok(userServiceImpl.getUserById(id));
    }
    @PostMapping("/update/password")
    public ResponseEntity<User> updatePasswordByUsername(@RequestParam("username") String username,
                                                         @RequestParam("newPassword") String newPassword){
        userServiceImpl.updatePasswordByUsername(username, newPassword);
        User user = userServiceImpl.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/addBookToWantToRead")
    public ResponseEntity<?> addBookToWantToRead(@RequestParam("userId") String userId,
                                                 @RequestParam("bookId") int bookId) {
        userServiceImpl.addBookToWantToRead(Integer.parseInt(userId), bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addBookToReading")
    public ResponseEntity<?> addBookToReading(@RequestParam("userId") int userId, @RequestParam("bookId") int bookId) {
        userServiceImpl.addBookToReading(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addBookToFinished")
    public ResponseEntity<?> addBookToFinished(@RequestParam("userId") int userId, @RequestParam("bookId") int bookId) {
        userServiceImpl.addBookToFinished(userId, bookId);
        return ResponseEntity.ok().build();
    }
}
