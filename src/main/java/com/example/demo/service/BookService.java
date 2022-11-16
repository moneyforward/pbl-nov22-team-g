package com.example.demo.service;

import com.example.demo.pojo.Book;
import com.example.demo.utils.MybBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class BookService {
    SqlSession sqlSession;
    public Book findBookByTitle(String title){
        Book result = null;
        try {
            sqlSession = MybBatisUtils.getSqlSession();
            result = sqlSession.getMapper(Book.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }
}
