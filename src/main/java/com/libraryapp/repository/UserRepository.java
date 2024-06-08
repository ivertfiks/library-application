package com.libraryapp.repository;

import com.libraryapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Autowired
    User loadUserByUsername(String username);
    @Autowired
    void delete(String username);
}
