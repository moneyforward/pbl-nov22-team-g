package com.example.demo.pojo;

import lombok.Data;

@Data
public class Book {
    private int BookID;
    private String Title;
    private String Author;
    private String ISBN;
    public Book(int bookID, String Title,String Author,String ISBN){
        this.BookID = bookID;
        this.Title=Title;
        this.Author =Author;
        this.ISBN =ISBN;
    }
}
