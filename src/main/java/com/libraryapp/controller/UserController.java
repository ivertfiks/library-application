package com.libraryapp.controller;

import com.libraryapp.entity.User;
import com.libraryapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("name") String name) {
        User user = userService.createUser(username, password, name);
        return ResponseEntity.ok(user);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser() {
//
//    }

}
