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
import java.util.Random;

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
    public String addAdmin(String email) {
        String password = getinitialpassword(8);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String newpassword = bCryptPasswordEncoder.encode(password);
        Admin admin = new Admin(email,newpassword);

        if(mapper.adminRegister(admin)){
            return password;
        }else{
            return null;
        }
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

    public String getinitialpassword(int length) {
        Random random = new Random();
        StringBuffer valSb = new StringBuffer();
        String charStr = "0123456789abcdefghijklmnopqrstuvwxyz";
        int charLength = charStr.length();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charLength);
            valSb.append(charStr.charAt(index));
        }
        return valSb.toString();

    }

}
