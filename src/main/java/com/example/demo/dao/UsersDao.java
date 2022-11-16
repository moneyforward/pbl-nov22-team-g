package com.example.demo.dao;

import com.example.demo.pojo.Users;

import java.util.List;

public interface UsersDao {
    List<Users> getUserList();
    Users checkUser(String NickName);
    boolean userRegister(Users users);
    boolean updateUser(Users users);
    boolean deleteUser(Users users);


}
