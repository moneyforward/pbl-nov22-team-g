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
    public Admin adminLogin(String email, String password){
        Admin admin = mapper.getAdminbyEmail(email);

        if(admin!=null && admin.getPassword().equals(password)){
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
