package com.libraryapp.service;

import com.libraryapp.entity.User;
import com.libraryapp.entity.exceptions.DuplicateUserException;
import com.libraryapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
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

    public void updatePasswordByUsername(String username, String newPassword){
        User user = userRepository.getUserByUsername(username);
        String encodedPassword = encoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
