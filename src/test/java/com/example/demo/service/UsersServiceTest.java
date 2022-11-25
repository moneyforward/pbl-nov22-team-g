package com.example.demo.service;

import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class UsersServiceTest {
@Autowired
UserServiceInter userServiceInter;
    @Test
    void userLogin() {
        userServiceInter.userLogin("123@mf.com","1234567");

    }
    @Test
    void userLoginnull() {
        userServiceInter.userLogin("","1111");}

    @Test
    void userUpdate(){
        userServiceInter.userUpdate(1,"123@mf.com","1234567","hello");
    }
    @Test
    void userUpdateemail() {
        userServiceInter.userUpdate(1,"","1234567","hello");
    }
    @Test
    void userUpdatenickname() {
        userServiceInter.userUpdate(1,"123@mf.com","1234567","");
    }
    @Test
    void userUpdatepassword() {
        userServiceInter.userUpdate(1,"123@mf.com","","hello");
    }

    @Test
    void userSignup() {
        userServiceInter.userSignup("333@mf.com","1234567","qyx");
    }
    @Test
    void userSignup1() {
        userServiceInter.userSignup("123@mf.com","12345","qyx");
    }
    @Test
    void userSignup2() {
        userServiceInter.userSignup("333@mf.com","1234567","hello");
    }

    @Test
    void getUserPofile() {
        userServiceInter.getUserPofile(1);
    }
}