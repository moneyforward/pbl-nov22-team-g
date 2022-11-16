package com.example.demo.DAO;

import com.example.demo.dao.UsersDao;
import com.example.demo.pojo.Users;
import com.example.demo.utils.MybBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UsersDaoTest {
    SqlSession sqlSession;
    @Test
    public void testfindlist(){


        sqlSession = MybBatisUtils.getSqlSession();

        UsersDao mapper = sqlSession.getMapper(UsersDao.class);
        List<Users> userList = mapper.getUserList();
        for (Users user : userList) {
            System.out.println(user);
        }



        sqlSession.close();

    }
    @Test
    public void testinsert(){
        sqlSession = MybBatisUtils.getSqlSession();
        UsersDao mapper = sqlSession.getMapper(UsersDao.class);
        Users test1 = new Users("abc","1234556","qyx@mf.com");
        if(mapper.userRegister(test1)){
            sqlSession.commit();
            System.out.println("Register ok!");
        }else{
            sqlSession.rollback();
            System.out.println("Register error!");

        }
        sqlSession.close();
    }
}
