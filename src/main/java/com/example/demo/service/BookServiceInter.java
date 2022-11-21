package com.example.demo.service;

import com.example.demo.pojo.Book;
import com.example.demo.pojo.BookList;
import com.example.demo.pojo.BorrowDetails;

import java.util.List;

public interface BookServiceInter {
    Book findBookByTitle(String title);

    List<BookList> getBookList();
    List<Book> getBookDetail(String title);
    void addReadPlan(String title, int userId);
    void reserveBook(int bookId, int userId);
    List<Book> getReadPlan(int userId);
    void deletePlan(String title, int userId);
    List<BorrowDetails> getRecord(String[] status,int userId);
    boolean addnewBook(String title,String author,String ISBN);
    boolean editBook(int BookID, String title,String author,String ISBN);
}
