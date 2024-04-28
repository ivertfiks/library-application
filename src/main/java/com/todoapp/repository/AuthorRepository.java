package com.todoapp.repository;

import com.todoapp.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    @Autowired
    Author getAuthorByName(String name);

}
