package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.CruddemoApplication;
import com.luv2code.springboot.cruddemo.customexceptions.NotFoundException;
import com.luv2code.springboot.cruddemo.dao.BookRepository;
import com.luv2code.springboot.cruddemo.entity.Author;
import com.luv2code.springboot.cruddemo.entity.Book;
import com.luv2code.springboot.cruddemo.service.interfaces.BookService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.*;


@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class BookServiceTests {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository theBookRepository;

    @Test
    public void getBooksTest(){
        when(theBookRepository.findAll()).thenReturn(Stream.of( new Book("Diffusion of Innovation minds","John","Learn How Innovations Are Put To Use","entrepreneurship")).collect(Collectors.toList()));
        assertEquals(1,bookService.findAll().size());
    }

    @Test
    public void getBooksByIdTest(){
        int bookId=1;
        Book book=new Book("Diffusion of Innovation minds","John","Learn How Innovations Are Put To Use","entrepreneurship");
        when(theBookRepository.findById(bookId)).thenReturn(java.util.Optional.of(book));
        assertEquals(book,bookService.findById(bookId));
    }

    @Test
    public void saveBooks(){
        Book book=new Book("Diffusion of Innovation minds","John","Learn How Innovations Are Put To Use","entrepreneurship");
        when(theBookRepository.save(book)).thenReturn(book);
        assertEquals(book,bookService.save(book));
    }

    @Test
    public void deleteBookTest(){
        Book book=new Book("Diffusion of Innovation minds","John","Learn How Innovations Are Put To Use","entrepreneurship");
        int bookId=1;
        bookService.deleteById(bookId);
        verify(theBookRepository,times(1)).deleteById(bookId);
    }


}
