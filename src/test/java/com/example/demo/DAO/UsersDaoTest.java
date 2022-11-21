package com.example.demo.DAO;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.UsersDao;
import com.example.demo.pojo.Admin;
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
//        Users test1 = new Users("abc","1234556","qyx@mf.com");
        if(mapper.userRegister("abcd","1234567","333@mf.com")){
            sqlSession.commit();
            System.out.println("Register ok!");
        }else{
            sqlSession.rollback();
            System.out.println("Register error!");

        }
        sqlSession.close();
    }
//    @Test
//    public void finduserbyID(){
//        sqlSession = MybBatisUtils.getSqlSession();
//        UsersDao mapper = sqlSession.getMapper(UsersDao.class);
//        Users test = mapper.getUserbyId(1);
//        System.out.println(test);
//        sqlSession.close();
//    }
    @Test
    public void updataUser(){
        sqlSession = MybBatisUtils.getSqlSession();
        UsersDao mapper = sqlSession.getMapper(UsersDao.class);
        Users test = mapper.getUserbyEmail("333@mf.com");
        test.setEmail("q123@mf.com");
        mapper.updateUser(test);
        sqlSession.commit();
    }
    @Test
    public void addAdmin(){
        sqlSession = MybBatisUtils.getSqlSession();
        AdminDao mapper = sqlSession.getMapper(AdminDao.class);
        Admin admin = mapper.getAdminbyEmail("admin@mf.com");
        System.out.println(admin);
    }
}
