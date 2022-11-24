package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    private int RecordID;
    private Date StartDate;
    private Date EndDate;
    private int BookID;
    private int UserID;
    private String Status;

    public Borrow(int recordID, Date startDate, Date endDate, int bookID, int userID, String Status) {
        RecordID = recordID;
        StartDate = startDate;
        EndDate = endDate;
        BookID = bookID;
        UserID = userID;
        this.Status = Status;
    }
}
