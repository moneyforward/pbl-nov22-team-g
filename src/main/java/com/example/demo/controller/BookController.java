package com.example.demo.controller;

import com.example.demo.pojo.BookList;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/bookList")
    @ResponseBody
    public List<BookList> getBookList(){
        return bookService.getBookList();
    }
}
