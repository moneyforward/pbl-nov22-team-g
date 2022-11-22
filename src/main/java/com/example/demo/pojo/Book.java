package com.example.demo.pojo;

import lombok.Data;

@Data
public class Book {
    private int BookID;
    private String Title;
    private String Authour;
    private String ISBN;
    public Book(String Title,String Authour,String ISBN){
        this.Title=Title;
        this.Authour =Authour;
        this.ISBN =ISBN;
    }
}
