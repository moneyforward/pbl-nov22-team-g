package com.example.demo.DAO;

import com.example.demo.dao.BookDao;
import com.example.demo.pojo.Book;
import com.example.demo.utils.MybBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class BookDaoTest {
    SqlSession sqlSession;
    @Test
    public void test(){


        sqlSession = MybBatisUtils.getSqlSession();
        BookDao mapper = sqlSession.getMapper(BookDao.class);
        Book result = mapper.findBookbyTitle("1984");


        sqlSession.close();

        System.out.println(result);

    }
}
