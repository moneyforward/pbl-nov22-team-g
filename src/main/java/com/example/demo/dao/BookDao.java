package com.example.demo.dao;

import com.example.demo.pojo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;


import java.util.List;
@Mapper
public interface BookDao {
    @Select("select title, author, count(*) stock, isbn from book WHERE BookID<>-1 GROUP BY title")
    List<BookList> getBookList();
    @Select("select * from Book where Title = #{title}")
    Book findBookbyTitle(String title);
    @Select("select * from Book where Author = #{Author}")
    BookDetail findBookbyAuthor(String Author);
    @Select("select * from Book where BookID = #{BookID}")
    Book findBookbyID(int BookID);
    @Insert("insert INTO Book(Title,Author,ISBN) values(#{Title},#{Author},#{ISBN})")
    boolean addBook(Book newbook);
    @Update("update Book set Title = #{title},Author = #{author},ISBN = #{ISBN} where BookID = #{BookID}")
    boolean updateUser(Book bookDetail);
    @Delete("delete from libsystem.Book where BookID = #{BookID}")
    boolean deletBook(int BookID);
    @Select("SELECT Book.bookid, title, author, status, count(*),isbn FROM (SELECT * FROM borrow WHERE Status<>'done') borrow RIGHT JOIN book ON borrow.bookid=book.bookid WHERE book.title=#{title} AND (status<>'done' or status is null) GROUP BY status")
    List<BookDetail> getBookDetailByTitle(String title);
    @Insert("INSERT INTO plans(bookTitle, userId) values(#{title}, #{userId})")
    void addReadPlan(@Param("title") String title, @Param("userId") int userId);
    @Insert("INSERT INTO borrow(StartDate, EndDate, Status, BookID, UserID) VALUES(NOW(), DATE_ADD(NOW(), INTERVAL 24 HOUR), 'pending', #{bookId}, #{userId})")
    void reverseBook(@Param("bookId") int bookId, @Param("userId")int userId);
    @Select("SELECT * from borrow where BookID =#{bookid} AND UserID=#{userid}")
    BorrowDetails findbookDetails(int bookid, int userid);
    @Update("update borrow set status = #{status}, enddate=NOW() where BookID = #{bookID}")
    boolean updatebookDetails(@Param("status") String status, @Param("bookID") int bookID);

    @Update("update borrow set status = #{status}, enddate=DATE_ADD(NOW(), INTERVAL 240 HOUR) where UserID = #{userID} AND status='pending'")
    boolean checkIn(@Param("status") String status, @Param("userID") int userID);

    @Select("""
            SELECT Book.bookid, title, author, status, count(*), isbn
            FROM (SELECT * FROM borrow WHERE status<>'done') borrow
            RIGHT JOIN book ON borrow.bookid=book.bookid WHERE book.title IN (SELECT bookTitle FROM plans WHERE userid=#{userId}) GROUP BY status, title                            
            """)
    List<BookDetail> getReadPlan(int userId);

    @Delete("DELETE FROM plans WHERE booktitle=#{title} AND userid=#{userId}")
    void deletePlan(String title, int userId);
    @Select("""
            <script>
            SELECT borrow.recordid, borrow.startdate, borrow.enddate, borrow.status, borrow.userid, borrow.bookid, book.title, book.author
            FROM borrow LEFT JOIN book ON borrow.bookid=book.bookid WHERE borrow.status IN
            <foreach item='status' index='index' collection='list' open='(' separator=',' close=')'>#{status}</foreach>
            AND borrow.userid=#{userId}
            </script>
            """)
    List<BorrowDetails> getRecord(@Param("list") String[] status, @Param("userId") int userId);
    @Update("UPDATE borrow SET Status='done' WHERE Status='pending' AND EndDate < NOW() AND userid=#{userId}")
    void updatePending(int userId);

    @Update("UPDATE borrow SET Status='overdue' WHERE Status='processing' AND EndDate < NOW() AND userid=#{userId}")
    void updateProcessing(@Param("userId") int userId);
    @Select("""
            select title, author, count(*) stock, isbn from book
            WHERE title LIKE CONCAT('%',#{query},'%') OR author LIKE CONCAT('%',#{query},'%') OR isbn LIKE CONCAT('%',#{query},'%')
            GROUP BY title
            """)
    List<BookList> searchBook(String query);
    @Select("""
            select title, author, count(*) stock, isbn from book
            GROUP BY title
            """)
    List<BookList> searchAllBook();
    @Select("""
            select bookid, title, author, isbn from book
            WHERE title LIKE CONCAT('%',#{query},'%') OR author LIKE CONCAT('%',#{query},'%') OR isbn LIKE CONCAT('%',#{query},'%')
            
            """)
    List<Book> searchSingleBook(String query);
    @Select("""
            <script>
            SELECT borrow.recordid, borrow.startdate, borrow.enddate, borrow.status, borrow.userid, borrow.bookid, book.title, book.author
            FROM borrow LEFT JOIN book ON borrow.bookid=book.bookid WHERE borrow.status IN
            <foreach item='status' index='index' collection='list' open='(' separator=',' close=')'>#{status}</foreach>
            AND borrow.userid=#{userId} AND (book.title LIKE CONCAT('%',#{query},'%') OR author LIKE CONCAT('%',#{query},'%') OR isbn LIKE CONCAT('%',#{query},'%'))
            </script>
            """)
    List<BookDetail> searchRecord(@Param("list") String[] status, String query, int userId);
    
    @Select("select recordid, startdate, enddate, book.bookid, userid, status, book.title, book.isbn from borrow JOIN book ON borrow.bookid=book.bookid where Status = #{status}" )
    List<Borrow> getoverdueBook(String status);

    @Update("UPDATE borrow SET Status='done' WHERE Status='pending' AND EndDate < NOW()")
    void checkPendingExpire();

    @Update("UPDATE borrow SET Status='overdue' WHERE Status='processing' AND EndDate < NOW()")
    void checkProcessingExpire();
}
