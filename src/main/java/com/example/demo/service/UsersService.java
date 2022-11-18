package com.example.demo.service;

import com.example.demo.dao.UsersDao;
import com.example.demo.pojo.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsersService implements UserServiceInter{

//    SqlSession sqlSession = MybBatisUtils.getSqlSession();
    @Autowired
    private UsersDao mapper;
//    UsersDao mapper = sqlSession.getMapper(UsersDao.class);
    @Override
    public String userLogin(String useremail, String password){

        Users user = mapper.getUserbyEmail(useremail);

        if(user.getPassword().equals(password)){
            return user.getNickname();
        }else{
            return null;
        }

    }
}
