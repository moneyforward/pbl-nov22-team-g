package com.example.demo.service;

import com.example.demo.pojo.Users;

public interface UserServiceInter {
    Users userLogin(String useremail, String password);
    String userSignup(String useremail,String password,String nickname);
}
