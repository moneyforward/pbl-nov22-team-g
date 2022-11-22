package com.example.demo.pojo;

import lombok.Data;

@Data
public class BookList {
    private String Title;
    private String Author;

    private String Stock;
    private String isbn;

    public BookList(String Title, String Author, String Stock, String isbn){
        this.Title = Title;
        this.Author = Author;
        this.Stock = Stock;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                ", Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", Stock='" + Stock + '\'' +
                '}';
    }
}
