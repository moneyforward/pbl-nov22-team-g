package com.example.demo.service;

import com.example.demo.pojo.Book;
import com.example.demo.pojo.BookDetail;
import com.example.demo.pojo.BookList;
import com.example.demo.pojo.BorrowDetails;

import java.util.List;

public interface BookServiceInter {
    BookDetail findBookByTitle(String title);
    Book findbookbyID(int BookID);
    List<BookList> getBookList();
    List<BookDetail> getBookDetail(String title);
    void addReadPlan(String title, int userId);
    void reserveBook(int bookId, int userId);
    List<BookDetail> getReadPlan(int userId);
    void deletePlan(String title, int userId);
    List<BorrowDetails> getRecord(String[] status,int userId);
    boolean addnewBook(String title,String author,String ISBN);
    boolean editBook(int BookID, String title,String author,String ISBN);
    List<BookList> searchBook(String query);
    List<BookList> searchSingleBook(String query);
    List<BorrowDetails> findoverdueBook(String status);
    String checkstatus(int userID);
    boolean returnBook(int BookID,int UserUD);

}
