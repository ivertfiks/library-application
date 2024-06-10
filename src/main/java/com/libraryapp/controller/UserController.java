package com.libraryapp.controller;

import com.libraryapp.entity.User;
import com.libraryapp.entity.exceptions.DuplicateUserException;
import com.libraryapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       @RequestParam("name") String name){
        try {
            return ResponseEntity.ok(userService.createUser(username, password, name));
        } catch (DuplicateUserException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/getUserByUsername")
    public ResponseEntity<User> getUserByUsername(@RequestParam("username") String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
    @DeleteMapping("/deleteUserByUsername")
    public ResponseEntity<?> deleteUserByUsername(@RequestParam("username") String username){
        User userToDelete = userService.getUserByUsername(username);
        if(userToDelete != null){
            userService.deleteUserByUsername(username);
            return ResponseEntity.ok(userToDelete);
        }else{
            return new ResponseEntity<>("Cannot remove user as it doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(@RequestParam("id") int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PostMapping("/updatePasswordByUsername")
    public ResponseEntity<User> updatePasswordByUsername(@RequestParam("username") String username,
                                                         @RequestParam("newPassword") String newPassword){
        userService.updatePasswordByUsername(username, newPassword);
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
