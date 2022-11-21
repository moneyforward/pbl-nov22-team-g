package com.example.demo.dao;

import com.example.demo.pojo.Admin;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminDao {
    @Select("select * from admin where Nickname = #{Nickname}")
    Admin getAdminbyNickname(String Nickname);
    @Insert("insert INTO admin(Nickname,Password) values(#{admin},#{Password})")
    boolean adminRegister(Admin admin);
}
