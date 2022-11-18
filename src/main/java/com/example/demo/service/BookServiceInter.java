package com.example.demo.service;

import com.example.demo.pojo.Book;
import com.example.demo.pojo.BookList;

import java.util.List;

public interface BookServiceInter {
    Book findBookByTitle(String title);

    List<BookList> getBookList();
}
