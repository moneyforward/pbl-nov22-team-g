package com.example.demo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Reservation {

    private int ReservationID;
    private int BookID;
    private int UserID;
    private Date Deadline;

    public Reservation(int reservationID, int bookID, int userID, Date deadline) {
        ReservationID = reservationID;
        BookID = bookID;
        UserID = userID;
        Deadline = deadline;
    }
    @Override
    public String toString() {
        return "Reservation{" +
                "ReservationID=" + ReservationID +
                ", BookID=" + BookID +
                ", UserID=" + UserID +
                ", Deadline=" + Deadline +
                '}';
    }

}
