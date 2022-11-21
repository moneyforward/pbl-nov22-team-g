package com.example.demo.service;


import com.example.demo.pojo.Admin;

public interface AdminServiceInter {
    Admin adminLogin(String nickname, String password);
    Boolean addAdmin(String nickname,String password);
}
