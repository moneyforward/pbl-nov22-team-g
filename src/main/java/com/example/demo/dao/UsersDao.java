package com.example.demo.dao;

import com.example.demo.pojo.Users;
//import lombok.Data;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
public interface UsersDao {
    List<Users> getUserList();
    @Select("select * from Users where Nickname = #{Nickname} ")
    Users checkUser(String NickName);
    @Insert("insert INTO users(Nickname,Password,Email) values(#{Nickname},#{Password},#{Email})")
    boolean userRegister(String Nickname,String Password,String Email);
    @Select("select * from users where Email = #{Email}")
    Users getUserbyEmail(String Email);
    @Select("select * from users where Nickname = #{Nickname}")
    Users getUserbyNickname(String Nickname);
    @Select("select * from users where UserID = #{ID}")
    Users getUserbyID(int ID);
    @Update("update Users set Nickname = #{Nickname},Password = #{Password},Email = #{Email} where UserID = #{UserID}")
    int updateUser(Users users);
    @Delete("delete from Users where UserID = #{UserID}")
    boolean deleteUser(Users users);


}
