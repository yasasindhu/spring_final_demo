package com.luv2code.springboot.cruddemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="book")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    @NotBlank(message = "Title may not be blank")
    private String title;


    @Column(name="author_name")
    @NotBlank(message = "AuthorName may not be blank")
    private String authorName;

    @Column(name="description")
    private String description;

    @Column(name="category")
    @NotBlank(message = "category may not be blank")
    private String category;

    public Book(String title, String authorName, String description, String category) {
        this.title = title;
        this.authorName = authorName;
        this.description = description;
        this.category = category;
    }
}
