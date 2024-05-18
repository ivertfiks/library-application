package com.libraryapp.service;

import com.libraryapp.entity.User;
import com.libraryapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public User createUser(String username, String password, String name){
        // TODO: check if user exists with this username, if exists throw exception (DuplicateUseException)
        return userRepository.save(new User(username, encoder.encode(password), name));
    }

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    // TODO: create method to get a list of users

    // TODO: create method to get a User by id

    // TODO: create method to delete User by username

    // TODO: create method to update User password by username

}
