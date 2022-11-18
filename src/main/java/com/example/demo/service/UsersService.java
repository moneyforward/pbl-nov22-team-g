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
    public Users userLogin(String useremail, String password){

        Users user = mapper.getUserbyEmail(useremail);

        if(user!=null && user.getPassword().equals(password)){
            return user;
        }else{
            return null;
        }

    }
    @Override
    public String userUpdate(int id,String email,String password, String nickname){
        Users users= mapper.getUserbyID(id);
        if(email != null){
            if(mapper.getUserbyEmail(email)==null){
                users.setEmail(email);
            }else{
                return "Email has been signup";
            }

        }
        if(password != null){
            users.setPassword(password);
        }
        if (nickname != null) {
            if(mapper.getUserbyNickname(nickname)==null){
                users.setNickname(nickname);
            }else{
                return "Nickname has been used";
            }

        }
        if(mapper.updateUser(users)==1){
            return null;
        }else{
            return "error";
        }

    }

    @Override
    public String userSignup(String email, String password, String nickname) {
        if(mapper.getUserbyEmail("email")!=null){
            return "Useremail had signup ";
        }
        if(mapper.getUserbyNickname("nickname")!= null){
            return "Username had signup";
        }

        if(mapper.userRegister(nickname,email,password)){
            return null;
        }else{
            return "Signup error";
        }
    }
}
