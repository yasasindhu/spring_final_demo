package com.luv2code.springboot.cruddemo.service.interfaces;

import com.luv2code.springboot.cruddemo.entity.Author;

import java.util.List;

public interface AuthorService {
     List<Author> findAll();

    Author findById(String theId);

    Author save(Author theAuthor);

     void deleteById(String theId);
}
