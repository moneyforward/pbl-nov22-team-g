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

        if(user!=null && user.getPassword().equals(password)){
            return user.getNickname();
        }else{
            return null;
        }

    }

    @Override
    public String userSignup(String useremail, String password, String nickname) {
        if(mapper.getUserbyEmail("useremail")!=null){
            return "Useremail had signup ";
        }
        if(mapper.getUserbyNickname("nickname")!= null){
            return "Username had signup";
        }

        if(mapper.userRegister(useremail,nickname,password)){
            return null;
        }else{
            return "Signup error";
        }

    }

}
