package com.libraryapp.service;

import com.libraryapp.entity.User;

import java.util.List;

public interface UserService {

    User getUser(int id);
    User saveUser(User user);
    User getUser(String username);
    List<User> getAllUsers();

}
