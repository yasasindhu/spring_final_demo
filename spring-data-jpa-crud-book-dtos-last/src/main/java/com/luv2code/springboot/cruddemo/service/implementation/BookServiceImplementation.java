package com.luv2code.springboot.cruddemo.service.implementation;

import com.luv2code.springboot.cruddemo.dao.BookRepository;
import com.luv2code.springboot.cruddemo.entity.Book;
import com.luv2code.springboot.cruddemo.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {

    @Autowired
    private BookRepository bookRepository;



    @Override
    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    @Override
    public Book findById(int theId) {

        Optional<Book> result = bookRepository.findById(theId);

        Book theBook;

        if(result.isPresent()){
            theBook=result.get();
        }
        else{
            throw new IllegalArgumentException("Did not find Book");
        }

        return theBook;
    }

    @Override
    public Book save(Book theBook) {

        bookRepository.save(theBook);

        return theBook;
    }

    @Override
    public void deleteById(int theId) {

        bookRepository.deleteById(theId);
    }
}
