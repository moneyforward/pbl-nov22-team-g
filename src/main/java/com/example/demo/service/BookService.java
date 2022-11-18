package com.example.demo.service;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.UsersDao;
import com.example.demo.pojo.Book;
import com.example.demo.pojo.BookList;
import com.example.demo.utils.MybBatisUtils;
import org.apache.ibatis.session.SqlSession;
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
    public List<BookList> getBookList(){
        return mapper.getBookList();
    }
}
