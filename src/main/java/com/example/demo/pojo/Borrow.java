package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.text.SimpleDateFormat;

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

    private String title;
    private String isbn;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String startStr;
    private String endStr;

    public Borrow(int recordID, Date startDate, Date endDate, int bookID, int userID, String Status, String title, String isbn) {
        RecordID = recordID;
        StartDate = startDate;
        EndDate = endDate;
        BookID = bookID;
        UserID = userID;
        this.Status = Status;
        this.title = title;
        this.isbn = isbn;

        this.startStr = sdf.format(startDate);
        this.endStr = sdf.format(endDate);
    }
}
