package com.example.demo.dao;

import com.example.demo.pojo.Book;
import com.example.demo.pojo.BookList;
import com.example.demo.pojo.Users;
import org.apache.ibatis.annotations.*;


import java.util.List;
@Mapper
public interface BookDao {
    @Select("select title, author, count(*) stock from book GROUP BY title")
    List<BookList> getBookList();
    @Select("select * from libsystem.Book where Title = #{title}")
    Book findBookbyTitle(String title);
    @Select("select * from libsystem.Book where Author = #{Author}")
    Book findBookbyAuthor(String Author);
    @Select("select * from libsystem.Book where BookID = #{BookID}")
    Book findBookbyID(int BookID);
    @Insert("insert INTO libsystem.Book(Title,Author,ISBN) values(#{Title},#{Author},#{ISBN})")
    boolean addBook(Book newbook);
    @Update("update book set Title = #{title},Author = #{author},ISBN = #{ISBN} where Title = #{BookID}")
    boolean updateUser(Book book);
    @Delete("delete from libsystem.Book where BookID = #{BookID}")
    boolean deletBook(int BookID);
}
