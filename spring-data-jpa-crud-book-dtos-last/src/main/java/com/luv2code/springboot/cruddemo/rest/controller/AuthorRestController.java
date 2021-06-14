package com.luv2code.springboot.cruddemo.rest.controller;

import com.luv2code.springboot.cruddemo.dto.AuthorPreciseDto;
import com.luv2code.springboot.cruddemo.entity.Author;
import com.luv2code.springboot.cruddemo.dto.AuthorDto;
import com.luv2code.springboot.cruddemo.customexceptions.NotFoundException;
import com.luv2code.springboot.cruddemo.service.interfaces.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AuthorRestController {


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private AuthorService authorService;

    String authorNotFound= "Author name not found";


    @GetMapping("/authors")
    public List<AuthorPreciseDto> getAuthor(){

        return authorService.findAll().stream().map(author -> modelMapper.map(author, AuthorPreciseDto.class))
                .collect(Collectors.toList());
    }



    @GetMapping("/authors/{authorName}/books")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable String  authorName){

        var theAuthor=authorService.findById(authorName);

        if(theAuthor==null){

            throw new NotFoundException(authorNotFound);
        }
        AuthorDto authorResponse = modelMapper.map(theAuthor, AuthorDto.class);

        return ResponseEntity.ok().body(authorResponse);

    }



    @PostMapping("/authors")
    public ResponseEntity<AuthorPreciseDto>  addAuthor(@Valid @RequestBody  AuthorPreciseDto theAuthorPreciseDto){

        var authorRequest = modelMapper.map(theAuthorPreciseDto, Author.class);

        var author = authorService.save(authorRequest);

        AuthorPreciseDto authorResponse = modelMapper.map(author, AuthorPreciseDto.class);

        return new ResponseEntity<>(authorResponse, HttpStatus.CREATED);
    }



    @PutMapping("/authors")
    public ResponseEntity<AuthorPreciseDto> updateAuthor(@RequestBody AuthorPreciseDto authorPreciseDto){


        var authorRequest = modelMapper.map(authorPreciseDto, Author.class);

        String authorName=authorRequest.getName();

        if(authorName==null){
            throw new NotFoundException(authorNotFound);
        }

        var author = authorService.save(authorRequest);


        AuthorPreciseDto authorResponse = modelMapper.map(author, AuthorPreciseDto.class);

        return ResponseEntity.ok().body(authorResponse);
    }

    @DeleteMapping("/authors/{authorId}")
    public String deleteBook(@PathVariable String authorId){

        var tempAuthor=authorService.findById(authorId);
        if(tempAuthor==null){
            throw new NotFoundException("Author id not found -"+authorId);
        }
        authorService.deleteById(authorId);
        return "Deleted Author id- "+authorId;
    }




}
