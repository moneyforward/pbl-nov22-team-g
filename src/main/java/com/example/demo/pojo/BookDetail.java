package com.example.demo.pojo;

import lombok.Data;

import java.util.HashMap;

//public record Book(String BookID,String Title, String Author,String ISBN){};
@Data
public class BookDetail {
    private int BookID;
    private String Title;
    private String Author;
    private String status;
    private Integer statusCount;
    private String ISBN;

    // private String ISBN;
    public BookDetail(int BookID, String Title, String Author, String status, Integer statusCount, String ISBN){
        this.BookID = BookID;
        this.Title = Title;
        this.Author = Author;
        this.status = status;
        this.statusCount = statusCount;
        this.ISBN = ISBN;
    }
    public BookDetail(String Title, String Author, String ISBN){
        this.Title = Title;
        this.Author = Author;
        this.ISBN =ISBN;
    }

    public HashMap<String, String> buildPublicInfo(){
        HashMap<String, String> map = new HashMap<>();
        map.put("title", Title);
        map.put("author", Author);
        map.put("pending", "0");
        map.put("avail", "0");
        map.put("processing", "0");
        return map;
    }

//    @Override
//    public String toString() {
//        return "Book{" +
//                "BookID=" + BookID +
//                ", Title='" + Title + '\'' +
//                ", Author='" + Author + '\'' +
////                ", Status='" + Status + '\'' +
//                ", ISBN='" + ISBN + '\'' +
//                '}';
//    }
}
