package com.example.demo.service;


import com.example.demo.pojo.Admin;
import com.example.demo.pojo.BorrowDetails;
import com.example.demo.pojo.Users;

import java.util.List;

public interface AdminServiceInter {
    Admin adminLogin(String nickname, String password);
    String addAdmin(String nickname);
    Users findUser(String keyWords);

}
