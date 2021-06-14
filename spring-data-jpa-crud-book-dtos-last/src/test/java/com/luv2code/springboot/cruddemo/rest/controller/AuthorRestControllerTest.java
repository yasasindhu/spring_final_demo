package com.luv2code.springboot.cruddemo.rest.controller;


import com.luv2code.springboot.cruddemo.dao.AuthorRepository;
import com.luv2code.springboot.cruddemo.entity.Author;
import com.luv2code.springboot.cruddemo.service.interfaces.AuthorService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorRestControllerTest {

    @InjectMocks
    AuthorRestController authorController;

    @Mock
    AuthorRepository authorRepository;

    protected MockMvc mvc;

    @LocalServerPort
    int randomServerPort;

    @Test
   void testGetAuthorListSuccess() throws URISyntaxException {

        RestTemplate restTemplate=new RestTemplate();

        final String baseUrl="http://localhost:"+randomServerPort+"/api/authors";

        URI uri=new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());

    }
   /* @Test
    public void testGetAuthorByNameSuccess() throws URISyntaxException {

        RestTemplate restTemplate=new RestTemplate();

        String name="sindhuja";

        final String baseUrl="http://localhost:"+randomServerPort+"/api/authors/"+name+"/books";

        URI uri=new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    public void testAddAuthor() throws Exception{
        String uri="/api/authors";
        Author author=new Author();
        author.setName("ram");
        String inputJson = super.mapToJson(author);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
       // String content = mvcResult.getResponse().getContentAsString();
       // assertEquals(content, "Product is created successfully");

    }

    @Test
    public void deleteAuthor() throws Exception {
        String uri = "api/authors/ram";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        //String content = mvcResult.getResponse().getContentAsString();
        //assertEquals(content, "Product is deleted successsfully");
    }*/



}