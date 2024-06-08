package com.libraryapp.controller;

import com.libraryapp.entity.User;
import com.libraryapp.entity.exceptions.DuplicateUserException;
import com.libraryapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("users/register")
    public ResponseEntity<User> create(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       @RequestParam("name") String name){
        try {
            return ResponseEntity.ok(userService.createUser(username, password, name));
        } catch (DuplicateUserException e) {
            throw new RuntimeException(e);
        }
    }
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
