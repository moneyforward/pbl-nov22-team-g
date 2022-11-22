package com.example.demo.service;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.UsersDao;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.BorrowDetails;
import com.example.demo.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements AdminServiceInter{
    @Autowired
    private AdminDao mapper;
    @Autowired
    private UsersDao usersDao;
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

    @Override
    public Users findUser(String keyWords) {
        Users user1 = usersDao.getUserbyNickname(keyWords);
        Users user2 = usersDao.getUserbyEmail(keyWords);
        if(user1 != null|| user2 != null){
            return user1!=null ? user1:user2;
        }else{
            return null;
        }
    }


}
