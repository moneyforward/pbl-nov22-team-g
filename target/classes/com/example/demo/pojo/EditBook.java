package com.example.demo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class EditBook {
    private int AdminID;
    private int BookID;
    private String info;
    private Date Timestamp;

    public EditBook(int adminID, int bookID, String info, Date timestamp) {
        AdminID = adminID;
        BookID = bookID;
        this.info = info;
        Timestamp = timestamp;
    }
}
