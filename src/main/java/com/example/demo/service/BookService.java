package com.example.demo.service;

import com.example.demo.dao.BookDao;
import com.example.demo.pojo.*;
import com.example.demo.SearchRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Component
public class BookService implements BookServiceInter{
    @Autowired
    private BookDao mapper;

    private SearchRank ranker;
    @PostConstruct
    private void init(){
        ranker = new SearchRank(mapper.searchAllBook());
    }

    @Override
    public BookDetail findBookByTitle(String title){
        return mapper.findBookbyTitle("title");
    }

    @Override
    public List<BookDetail> getBookDetail(String title){
        return mapper.getBookDetailByTitle(title);
    }

    @Override
    public List<BookList> getBookList(){
        return mapper.getBookList();
    }

    @Override
    public void addReadPlan(String title, int userId){mapper.addReadPlan(title, userId);}
    @Override
    public void reserveBook(int bookId, int userId){mapper.reverseBook(bookId, userId);}
    @Override
    public List<BookDetail> getReadPlan(int userId){
        mapper.updatePending(userId);
        return mapper.getReadPlan(userId);
    }
    @Override
    public void deletePlan(String title, int userId){mapper.deletePlan(title, userId);}
    @Override
    public List<BorrowDetails> getRecord(String[] status, int userId){
        mapper.updateProcessing(userId);
        return mapper.getRecord(status, userId);
    }

    @Override
    public boolean addnewBook(String title, String author, String ISBN) {

        return mapper.addBook(new Book(-1, title,author,ISBN));
    }

    @Override
    public boolean editBook(int BookID,String title, String author, String ISBN) {

        Book book = mapper.findBookbyID(BookID);
        book.setAuthor(author);
        book.setISBN(ISBN);
        book.setTitle(title);
        return mapper.updateUser(book);
    }

    @Override
    public List<BookList> searchBook(String query){
        return ranker.getRankList(query.toLowerCase());
    }

    @Override
    public String checkstatus(int userID) {
        int count = mapper.getRecord(new String[]{"processing"},userID).size();
        if(count >10){
            return "Check out books up to 10!";
        }
        count = mapper.getRecord(new String[]{"pending"},userID).size();
        if(count >5){
            return "Reservation up to 5!";
        }
        if(mapper.getRecord(new String[]{"overdue"},userID).size() != 0){
            return "Have overdue book!";
        }
        if(mapper.getRecord(new String[]{"baned"},userID).size() != 0){
            return "Baned by admin";
        }
        return null;
    }
    // for the user profile init, need less time
    public String getStatus(int userID) {
//        int count = mapper.getRecord(new String[]{"processing"},userID).size();
//        if(count >10){
//            return "0";
//        }
        if(mapper.getRecord(new String[]{"overdue"},userID).size() != 0){
            return "1";
        }
//        if(mapper.getRecord(new String[]{"baned"},userID).size() != 0){
//            return "2";
//        }
        return "-1";
    }


    @Override
    public List<Book> searchSingleBook(String query) {
        return mapper.searchSingleBook(query);
    }

    @Override
    public List<Borrow> findoverdueBook(String status) {
        return mapper.getoverdueBook(status);
    }

    @Override
    public boolean returnBook(int BookID) {
//        BorrowDetails borrowDetails =mapper.findbookDetails(BookID,UserID);
        return mapper.updatebookDetails("done", BookID);
    }

    @Override
    public Book findbookbyID(int BookID) {
        return mapper.findBookbyID(BookID);
    }
}
