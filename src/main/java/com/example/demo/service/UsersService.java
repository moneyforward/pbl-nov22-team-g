package com.example.demo.service;

import com.example.demo.dao.UsersDao;
import com.example.demo.pojo.Users;
import com.example.demo.utils.MybBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class UsersService {
    SqlSession sqlSession = MybBatisUtils.getSqlSession();
    UsersDao mapper = sqlSession.getMapper(UsersDao.class);

    public String userLogin(String useremail, String password){
        Users user = mapper.getUserbyEmail(useremail);
        if(user.getPassword().equals(password)){
            return user.getNickname();
        }else{
            return null;
        }

    }
}
