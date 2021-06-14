package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.AuthorRepository;
import com.luv2code.springboot.cruddemo.entity.Author;
import com.luv2code.springboot.cruddemo.service.interfaces.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorServiceTests {

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorRepository theAuthorRepository;

    public AuthorServiceTests() {
    }


    @Test
    public void getAuthorsTest(){
        when(theAuthorRepository.findAll()).thenReturn(Stream.of( new Author("Craig")).collect(Collectors.toList()));
        assertEquals(1,authorService.findAll().size());
    }

    @Test
    public void getAuthorsById(){
        String authorName="Craig";
        Author author=new Author();
        author.setName(authorName);
        when(theAuthorRepository.findById(authorName)).thenReturn(java.util.Optional.of(author));
        assertEquals(author,authorService.findById(authorName));
    }

    @Test
    public void saveAuthors(){
        Author author=new Author("John");
        when(theAuthorRepository.save(author)).thenReturn(author);
        assertEquals(author,authorService.save(author));
    }

    @Test
    public void deleteAuthorsById(){
        String authId="Craig";
        authorService.deleteById(authId);
        verify(theAuthorRepository,times(1)).deleteById(authId);
    }

}
