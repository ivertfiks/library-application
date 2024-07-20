package com.libraryapp.controller;

import com.libraryapp.entity.User;
import com.libraryapp.entity.exceptions.DuplicateUserException;
import com.libraryapp.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserServiceImpl userServiceImpl;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("name") String name){
        try {
            return ResponseEntity.ok(userServiceImpl.createUser(username, password, name));
        } catch (DuplicateUserException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User loggedInUser = (User) authentication.getPrincipal();
        log.info("Logged in user: " + loggedInUser.getUsername());
        return ResponseEntity.ok("Successfully signed in user");
    }
}
