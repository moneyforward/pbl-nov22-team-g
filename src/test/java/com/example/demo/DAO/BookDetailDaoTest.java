package com.example.demo.DAO;

import com.example.demo.dao.BookDao;
import com.example.demo.pojo.Book;
import com.example.demo.pojo.BookDetail;
import com.example.demo.pojo.Borrow;
import com.example.demo.pojo.BorrowDetails;
import com.example.demo.utils.MybBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class BookDetailDaoTest {
    SqlSession sqlSession;
//    @Test
//    public void test(){
//
//
//        sqlSession = MybBatisUtils.getSqlSession();
//        BookDao mapper = sqlSession.getMapper(BookDao.class);
//        BookDetail result = mapper.findBookbyTitle("1984");
//
//
//        sqlSession.close();
//
//        System.out.println(result);
//
//    }
    @Test
    public void updateTest(){
        sqlSession = MybBatisUtils.getSqlSession();
        BookDao mapper= sqlSession.getMapper(BookDao.class);
        Book result = mapper.findBookbyID(114);
        result.setAuthor("hhhh");
        mapper.updateUser(result);
        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void overduefind(){
        sqlSession = MybBatisUtils.getSqlSession();
        BookDao mapper= sqlSession.getMapper(BookDao.class);
        List<Borrow> result = mapper.getoverdueBook("overdue");
        System.out.println(result);
        sqlSession.close();

    }
}
