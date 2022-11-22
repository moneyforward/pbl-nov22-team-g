package com.example.demo.service;

import com.example.demo.pojo.Users;

public interface UserServiceInter {
    Users userLogin(String useremail, String password);
    String userSignup(String useremail,String password,String nickname);
    String userUpdate(int ID,String email,String password, String nickname);
    Users getUserPofile(int ID);
}
