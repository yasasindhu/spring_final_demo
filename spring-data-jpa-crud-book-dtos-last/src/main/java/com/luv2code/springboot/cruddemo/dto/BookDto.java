package com.luv2code.springboot.cruddemo.dto;

import lombok.Data;

@Data
public class BookDto {
    private int id;
    private String title;
    private String authorName;
    private String description;
    private String category;


}
