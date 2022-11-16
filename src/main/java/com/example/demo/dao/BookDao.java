package com.example.demo.dao;

import com.example.demo.pojo.Book;


import java.util.List;

public interface BookDao {
    List<Book> getBookList();
    Book findBook(String title);
    boolean addBook(Book newbook);
    boolean deletBook(Book newbook);
}
