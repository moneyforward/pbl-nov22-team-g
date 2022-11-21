package com.example.demo.pojo;

import lombok.Data;

import java.util.HashMap;

//public record Book(String BookID,String Title, String Author,String ISBN){};
@Data
public class Book {
    private int BookID;
    private String Title;
    private String Author;
    private String status;
    private int statusCount;

    // private String ISBN;
    public Book(int BookID, String Title, String Author, String status, int statusCount){
        this.BookID = BookID;
        this.Title = Title;
        this.Author = Author;
        this.status = status;
        this.statusCount = statusCount;
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
