package com.libraryapp.repository;

import com.libraryapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User getUserByUsername(String username);

    void deleteUserByUsername(String username);
}
