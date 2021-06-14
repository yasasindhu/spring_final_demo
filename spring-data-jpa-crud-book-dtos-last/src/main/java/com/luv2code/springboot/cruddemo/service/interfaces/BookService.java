package com.luv2code.springboot.cruddemo.service.interfaces;

import com.luv2code.springboot.cruddemo.entity.Book;


import java.util.List;

public interface BookService {

     List<Book> findAll();

     Book findById(int theId);

     Book save(Book theBook);

     void deleteById(int theId);

}
