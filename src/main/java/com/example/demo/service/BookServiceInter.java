package com.example.demo.service;

import com.example.demo.pojo.Book;

public interface BookServiceInter {
    Book findBookByTitle(String title);
}
