package com.example.demo.service;

public interface UserServiceInter {
    String userLogin(String useremail, String password);
    String userSignup(String useremail,String password,String nickname);
}
