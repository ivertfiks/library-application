package com.libraryapp.service;

import com.libraryapp.entity.User;
import com.libraryapp.entity.exceptions.DuplicateUserException;
import com.libraryapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public User createUser(String username, String password, String name) throws DuplicateUserException {
        User user = userRepository.loadUserByUsername(username);
        if(user == null) {
            return userRepository.save(new User(username, encoder.encode(password), name));
        }
        throw new DuplicateUserException("Duplicate user: " + username);
    }

    public User getUserByUsername(String username){
        return userRepository.loadUserByUsername(username);
    }

    public void deleteUserByUsername(String username){
        userRepository.delete(username);
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).get();
    }

    public void updatePasswordByUsername(String username, String newPassword){
        User user = userRepository.loadUserByUsername(username);
        String encodedPassword = encoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
