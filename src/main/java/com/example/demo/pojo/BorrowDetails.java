package com.example.demo.pojo;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class BorrowDetails {
    private String RecordID;
    private Date StartDate;
    private Date EndDate;
    private String Status;
    private int UserID;
    private int BookID;
    private String title;
    private String author;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String startStr;
    private String endStr;


    public BorrowDetails(String recordID, Date startDate, Date endDate, String status, int userID, int bookID, String title, String author) {
        RecordID = recordID;
        StartDate = startDate;
        EndDate = endDate;
        Status = status;
        UserID = userID;
        BookID = bookID;
        this.author = author;
        this.title = title;

        this.startStr = sdf.format(startDate);
        this.endStr = sdf.format(endDate);
    }
}
