package com.example.demo.service;

import com.example.demo.dao.AdminDao;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminServiceInter{
    @Autowired
    private AdminDao mapper;
    @Override
    public Admin adminLogin(String nickname, String password){
        Admin admin = mapper.getAdminbyNickname("nickname");

        if(admin!=null && admin.getPassword().equals(password)){
            return admin;
        }else{
            return null;
        }
    }

}
