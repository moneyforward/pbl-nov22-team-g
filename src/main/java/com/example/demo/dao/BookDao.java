package com.example.demo.dao;

import com.example.demo.pojo.Book;
import com.example.demo.pojo.BookList;
import com.example.demo.pojo.BorrowDetails;
import org.apache.ibatis.annotations.*;
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
    @Select("SELECT book.bookid, title, author, status, count(*),isbn FROM borrow RIGHT JOIN book ON borrow.bookid=book.bookid WHERE book.title=#{title} AND (status<>'done' or status is null) GROUP BY status")
    List<Book> getBookDetailByTitle(String title);
    @Insert("INSERT INTO plans(bookTitle, userId) values(#{title}, #{userId})")
    void addReadPlan(String title, int userId);
    @Insert("INSERT INTO borrow(StartDate, EndDate, Status, BookID, UserID) VALUES(NOW(), DATE_ADD(NOW(), INTERVAL 24 HOUR), 'pending', #{bookId}, #{userId})")
    void reverseBook(int bookId, int userId);

    @Select("""
            SELECT ifnull(avail.bookid,0), plans.booktitle, avail.author, avail.status, ifnull(avail.statusCount, 0), isbn FROM
            (SELECT book.bookid, title, author, status, count(*) statusCount,isbn
            FROM borrow RIGHT JOIN book ON borrow.bookid=book.bookid
            WHERE book.title IN (SELECT booktitle FROM plans WHERE userid=#{userId}) AND (status is null) GROUP BY status) avail
            RIGHT JOIN plans ON avail.title=plans.bookTitle
            """)
    List<Book> getReadPlan(int userId);

    @Delete("DELETE FROM plans WHERE booktitle=#{title} AND userid=#{userId})")
    void deletePlan(String title, int userId);
    @Select("""
            <script>
            SELECT borrow.recordid, borrow.startdate, borrow.enddate, borrow.status, borrow.userid, borrow.bookid, book.title, book.author
            FROM borrow LEFT JOIN book ON borrow.bookid=book.bookid WHERE borrow.status IN
            <foreach item='status' index='index' collection='list' open='(' separator=',' close=')'>#{status}</foreach>
            AND borrow.userid=#{userId}
            </script>
            """)
    List<BorrowDetails> getRecord(@Param("list") String[] status, int userId);
    @Update("UPDATE borrow SET Status='done' WHERE Status='pending' AND EndDate < NOW() AND userid=#{userId}")
    void updatePending(int userId);

    @Update("UPDATE borrow SET Status='overdue' WHERE Status='processing' AND EndDate < NOW() AND userid=#{userId}")
    void updateProcessing(int userId);
}
