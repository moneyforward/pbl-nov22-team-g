package com.example.demo.service;

import com.example.demo.pojo.Admin;
import com.example.demo.pojo.Users;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest

class AdminServiceTest {
@Autowired
private AdminServiceInter adminService;
    @Test
    void adminLogin() {
        String email = "admin@mf.com";
        String password = "123";
        adminService.adminLogin(email,password);

    }
    @Test
    void adminLoginnull() {
        String email = null;
        String password = "123";
        Admin admin = adminService.adminLogin(email,password);
        System.out.println(admin);
    }

    @Test
    void addAdmin() {
        String email = "admin2@mf.com";
        String password = adminService.addAdmin(email);

    }
    @Test
    void addAdminnull() {
        String email = null;
        String password = adminService.addAdmin(email);

    }

    @Test
    void findUser() {
        String keywords = "hahaha";
        Users user = adminService.findUser(keywords);
    }
    @Test
    void findUsernull() {
        String keywords = "hello";
        Users user = adminService.findUser(keywords);
    }

    @Test
    void getinitialpassword() {
        int i = 3;
        String password = adminService.getinitialpassword(i);
    }

    @Test
    void banUser() {
        adminService.banUser(3);
        adminService.unBanUser(3);
    }
}