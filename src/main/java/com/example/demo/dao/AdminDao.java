package com.example.demo.dao;

import com.example.demo.pojo.Admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminDao {
    @Select("select * from amdmin where Nickname = #{Nickname}")
    Admin getAdminbyNickname(String Nickname);
}
