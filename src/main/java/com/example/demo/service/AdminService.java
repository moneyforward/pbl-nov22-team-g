package com.example.demo.service;

import com.example.demo.dao.AdminDao;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminServiceInter{
    @Autowired
    private AdminDao mapper;
    @Override
    public Admin adminLogin(String email, String password){
        Admin admin = mapper.getAdminbyEmail(email);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(admin!=null && bCryptPasswordEncoder.matches(password,admin.getPassword())){
            return admin;
        }else{
            return null;
        }
    }

    @Override
    public Boolean addAdmin(String email, String Password) {
        Admin admin = new Admin(email,Password);
        return mapper.adminRegister(admin);
    }
}
