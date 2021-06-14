package com.luv2code.springboot.cruddemo.rest.controller;


import com.luv2code.springboot.cruddemo.entity.Book;
import com.luv2code.springboot.cruddemo.dto.BookDto;
import com.luv2code.springboot.cruddemo.customexceptions.NotFoundException;
import com.luv2code.springboot.cruddemo.service.interfaces.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookRestController {

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private BookService bookService;

    String bookNotFound= "Book id not found";


    @GetMapping("/books")
    public List<BookDto> getBooks(){

        return bookService.findAll().stream().map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }


    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable int bookId){

        var theBook=bookService.findById(bookId);

        if(theBook==null){
            throw new NotFoundException(bookNotFound);
        }

        BookDto bookResponse = modelMapper.map(theBook, BookDto.class);

        return ResponseEntity.ok().body(bookResponse);
    }


    @PostMapping("/books")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto theBookDto){


        var bookRequest = modelMapper.map(theBookDto, Book.class);

        bookRequest.setId(0);

        var book = bookService.save(bookRequest);

        BookDto bookResponse = modelMapper.map(book, BookDto.class);

        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }


    @PutMapping("/books")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto){

        var bookRequest = modelMapper.map(bookDto, Book.class);

        int bookId= bookRequest.getId();

        var theBook=bookService.findById(bookId);

        if(theBook==null){
            throw new NotFoundException(bookNotFound);
        }

        var book = bookService.save(bookRequest);

        BookDto bookResponse = modelMapper.map(book, BookDto.class);

        return ResponseEntity.ok().body(bookResponse);

    }

    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable int bookId){

        var tempBook=bookService.findById(bookId);

        if(tempBook==null){
            throw new NotFoundException(bookNotFound);
        }

        bookService.deleteById(bookId);

        return "Deleted Book id- "+bookId;
    }
}
