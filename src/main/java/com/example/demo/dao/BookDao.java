package com.example.demo.dao;

import com.example.demo.pojo.Book;
import com.example.demo.pojo.BookList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;
@Mapper
public interface BookDao {
    @Select("select title, author, count(*) stock from book GROUP BY title")
    List<BookList> getBookList();
    @Select("select * from libsystem.Book where Title = #{title}")
    Book findBookbyTitle(String title);
    @Select("select * from libsystem.Book where Author = #{Author}")
    Book findBookbyAuthor(String Author);
    @Insert("insert INTO libsystem.Book(Title,Author,ISBN) values(#{Title},#{Author},#{ISBN})")
    boolean addBook(Book newbook);
    @Delete("delete from libsystem.Book where BookID = #{BookID}")
    boolean deletBook(int BookID);
    @Select("SELECT book.bookid, title, author, status, count(*) FROM borrow RIGHT JOIN book ON borrow.bookid=book.bookid WHERE book.title=#{title} AND (status<>'done' or status is null) GROUP BY status")
    List<Book> getBookDetailByTitle(String title);
}
