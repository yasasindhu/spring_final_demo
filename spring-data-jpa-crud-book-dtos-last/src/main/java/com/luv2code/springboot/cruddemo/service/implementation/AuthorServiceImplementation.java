package com.luv2code.springboot.cruddemo.service.implementation;

import com.luv2code.springboot.cruddemo.dao.AuthorRepository;
import com.luv2code.springboot.cruddemo.entity.Author;
import com.luv2code.springboot.cruddemo.service.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImplementation implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public List<Author> findAll() {

        return authorRepository.findAll();
    }

    @Override
    public Author findById(String theId) {

        Optional<Author> result = authorRepository.findById(theId);

        Author theAuthor;

        if(result.isPresent()){
            theAuthor=result.get();
        }
        else{
            throw new IllegalArgumentException("Did not find author");
        }

        return theAuthor;
    }

    @Override
    public Author save(Author theAuthor) {

        authorRepository.save(theAuthor);

        return theAuthor;
    }

    @Override
    public void deleteById(String theId) {
        authorRepository.deleteById(theId);
    }


}
