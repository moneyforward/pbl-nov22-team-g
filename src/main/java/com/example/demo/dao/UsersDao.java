package com.example.demo.dao;

import com.example.demo.pojo.Users;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UsersDao {
    List<Users> getUserList();
    @Select("select * from libsystem.Useres where Nickname = #{Nickname} ")
    Users checkUser(String NickName);
    boolean userRegister(Users users);
    @Select("select * from libsystem.Users where Email = #{Email}")
    Users getUserbyEmail(String Email);
    @Update("update libsystem.Users set Nickname = #{Nickname},Password = #{Password},Email = #{Email} where UserID = #{UserID}")
    int updateUser(Users users);
    @Delete("delete from libsystem.Users where UserID = #{UserID}")
    boolean deleteUser(Users users);


}
