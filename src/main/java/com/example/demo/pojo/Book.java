package com.example.demo.pojo;

import lombok.Data;

//public record Book(String BookID,String Title, String Author,String ISBN){};
@Data
public class Book {
    private int BookID;
    private String Title;
    private String Author;
//    private String Status;
    private String ISBN;
    public Book(int BookID,String Title, String Author, String ISBN/*, String Status*/){
        this.BookID = BookID;
        this.Title = Title;
        this.Author = Author;
        this.ISBN = ISBN;
//        this.Status = Status;
    }
    public Book(String Title, String Author, String ISBN){
        this.Title = Title;
        this.Author = Author;
        this.ISBN =ISBN;
    }

    @Override
    public String toString() {
        return "Book{" +
                "BookID=" + BookID +
                ", Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
//                ", Status='" + Status + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
