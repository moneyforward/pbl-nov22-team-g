package com.example.demo.dao;

import com.example.demo.pojo.Admin;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminDao {
    @Select("select * from admin where Email = #{Email}")
    Admin getAdminbyEmail(String Email);
    @Insert("insert INTO admin(Email,Password) values(#{Email},#{Password})")
    boolean adminRegister(Admin admin);
    @Insert("INSERT INTO borrow(StartDate, EndDate, Status, BookID, UserID) values(NOW(), NOW(), 'baned', -1, #{userId})")
    void banUser(int userId);

    @Delete("DELETE FROM borrow WHERE userID=#{userId} AND bookID=-1")
    void unBanUser(int userId);
}
