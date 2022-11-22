package com.example.demo.DAO;

import com.example.demo.dao.BookDao;
import com.example.demo.pojo.BookDetail;
import com.example.demo.utils.MybBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class BookDetailDaoTest {
    SqlSession sqlSession;
    @Test
    public void test(){


        sqlSession = MybBatisUtils.getSqlSession();
        BookDao mapper = sqlSession.getMapper(BookDao.class);
        BookDetail result = mapper.findBookbyTitle("1984");


        sqlSession.close();

        System.out.println(result);

    }
}
