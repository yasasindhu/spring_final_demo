package com.luv2code.springboot.cruddemo.rest.controller;

import com.luv2code.springboot.cruddemo.dao.BookRepository;

import com.luv2code.springboot.cruddemo.service.interfaces.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookRestControllerTest {

    @InjectMocks
    BookRestController bookRestController;

    @Mock
    BookRepository bookRepository;

    @Mock
    BookService bookService;

    protected MockMvc mvc;



    @LocalServerPort
    int randomServerPort;

    @Test
     void testGetBooksListSuccess() throws URISyntaxException {
        RestTemplate restTemplate=new RestTemplate();

        final String baseUrl="http://localhost:"+randomServerPort+"/api/books";
        URI uri=new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

    }
    /*@Test
    public void testGetBookByIdSuccess() throws URISyntaxException {
        RestTemplate restTemplate=new RestTemplate();
        int bookId=1;
        final String baseUrl="http://localhost:"+randomServerPort+"/api/books/"+bookId;
        URI uri=new URI(baseUrl);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());

    }


    @Test
    public void deleteBook() throws Exception {
        int bookId=1;
        final String uri="http://localhost:"+randomServerPort+"/api/books/"+bookId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders. delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        //String content = mvcResult.getResponse().getContentAsString();
        //assertEquals(content, "Product is deleted successsfully");
    }*/


}