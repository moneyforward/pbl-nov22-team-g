package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

class BookServiceTest {
@Autowired
private BookServiceInter bookService;
    @Test
    void findBookByTitle() {
        String title = "1984";
        bookService.findBookByTitle(title);
    }

    @Test
    void getBookDetail() {
        String title = "1984";
        bookService.getBookDetail(title);
    }

    @Test
    void getBookList() {
        bookService.getBookList();
    }

    @Test
    void addReadPlan() {
        bookService.addReadPlan("Animal Farm",3);
    }

    @Test
    void reserveBook() {
        bookService.reserveBook(6,3);
    }

    @Test
    void getReadPlan() {
        bookService.getReadPlan(3);
    }

    @Test
    void deletePlan() {
        bookService.deletePlan("1984",1);
    }

    @Test
    void getRecord() {
        bookService.getRecord(new String[]{"reverse"},3);
    }

    @Test
    void addnewBook() {
        bookService.addnewBook("1982","hhh","ISBN");
    }

    @Test
    void editBook() {
        bookService.editBook(2,"1984","hhh","ISBN123");
    }

    @Test
    void searchBook() {
        bookService.searchBook("");
    }

    @Test
    void checkstatus() {
        bookService.checkstatus(3);
    }
    @Test
    void checkstatus4() {
        bookService.checkstatus(4);
    }
    @Test
    void minDistance(){
        bookService.minDistance("hello","nihao");
    }
    @Test
    void getStatus2() {
        bookService.getStatus(4);

    }
    @Test
    void getStatus1() {
        bookService.getStatus(3);

    }
    @Test
    void getStatus11() {
        bookService.getStatus(5);

    }

    @Test
    void searchSingleBook() {
        bookService.searchSingleBook("done");
    }

    @Test
    void findoverdueBook() {
        bookService.findoverdueBook("overdue");
    }

    @Test
    void returnBook() {
        bookService.returnBook(2);
    }

    @Test
    void findbookbyID() {
        bookService.findbookbyID(2);
    }
}