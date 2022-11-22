package com.example.demo.service;

import com.example.demo.dao.BookDao;
import com.example.demo.pojo.Book;
import com.example.demo.pojo.BookList;
import com.example.demo.pojo.BorrowDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BookServiceInter{
    @Autowired
    private BookDao mapper;
    @Override
    public Book findBookByTitle(String title){
        return mapper.findBookbyTitle("title");
    }

    @Override
    public List<Book> getBookDetail(String title){
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
    public List<Book> getReadPlan(int userId){
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

        return mapper.addBook(new Book(title,author,ISBN));
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
    public String checkstatus(int userID) {
        int count = mapper.getRecord(new String[]{"processing"},userID).size();
        if(count >10){
            return "check out books up to 10 !";
        }
        if(mapper.getRecord(new String[]{"overdue"},userID)!=null){
            return "You have to return the overdue book first!";
        }
        return null;

    }

    @Override
    public List<BorrowDetails> findoverdueBook(String status) {
        return mapper.getoverdueBook(status);
    }
}
