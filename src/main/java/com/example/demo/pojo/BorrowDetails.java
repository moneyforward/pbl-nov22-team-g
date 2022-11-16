package com.example.demo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class BorrowDetails {
    private String RecordID;
    private Date StartDate;
    private Date EndDate;
    private int Status;
    private int UserID;
    private int BookID;

    public BorrowDetails(String recordID, Date startDate, Date endDate, int status, int userID, int bookID) {
        RecordID = recordID;
        StartDate = startDate;
        EndDate = endDate;
        Status = status;
        UserID = userID;
        BookID = bookID;
    }
}
