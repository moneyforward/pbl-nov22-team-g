package com.example.demo.controller;

import com.example.demo.pojo.Book;
import com.example.demo.pojo.BookList;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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

    @RequestMapping("/bookDetail")
    @ResponseBody
    public HashMap<String, String> getBook(String title){
        List<Book> bookStatus = bookService.getBookDetail(title);

        HashMap<String, String> bookDetails = bookStatus.get(0).buildPublicInfo();
        for(Book book: bookStatus){
            String status =book.getStatus() != null? book.getStatus():"avail";

            bookDetails.put(status, book.getStatusCount()+"");
        }

        return bookDetails;
    }
}
