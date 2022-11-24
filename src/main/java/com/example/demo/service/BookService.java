package com.example.demo.service;

import com.example.demo.dao.BookDao;
import com.example.demo.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService implements BookServiceInter{
    @Autowired
    private BookDao mapper;
    @Override
    public BookDetail findBookByTitle(String title){
        return mapper.findBookbyTitle("title");
    }

    @Override
    public List<BookDetail> getBookDetail(String title){
        return mapper.getBookDetailByTitle(title);
    }

    @Override
    public List<BookList> getBookList(){
        return mapper.getBookList();
    }

    @Override
    public void addReadPlan(String title, int userId){mapper.addReadPlan(title, userId);}
    @Override
    public void reserveBook(int bookId, int userId){mapper.reverseBook(bookId, userId);}
    @Override
    public List<BookDetail> getReadPlan(int userId){
        mapper.updatePending(userId);
        return mapper.getReadPlan(userId);
    }
    @Override
    public void deletePlan(String title, int userId){mapper.deletePlan(title, userId);}
    @Override
    public List<BorrowDetails> getRecord(String[] status, int userId){
        mapper.updateProcessing(userId);
        return mapper.getRecord(status, userId);
    }

    @Override
    public boolean addnewBook(String title, String author, String ISBN) {

        return mapper.addBook(new Book(-1, title,author,ISBN));
    }

    @Override
    public boolean editBook(int BookID,String title, String author, String ISBN) {

        Book book = mapper.findBookbyID(BookID);
        book.setAuthor(author);
        book.setISBN(ISBN);
        book.setTitle(title);
        return mapper.updateUser(book);
    }

    private int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) {
            // 从i个字符变成0个字符，需要i步（删除）
            dp[i][0] = i;
        }
        for (int i = 0; i < word2.length() + 1; i++) {
            // 当从0个字符变成i个字符，需要i步(增加)
            dp[0][i] = i;
        }
        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                //当相同的时，dp[i][j] = dp[i - 1][j - 1]
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //当不同的时候，我们需要求三种操作的最小值
                    //其中dp[i - 1][j - 1]表示的是替换，dp[i - 1][j]表示删除字符，do[i][j - 1]表示的是增加字符
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
    @Override
    public List<BookList> searchBook(String query){
        List<BookList> recallResult = mapper.searchBook(query);

        HashMap<BookList, Integer> candidateSet = new HashMap<>();
        // calculate score
        for(BookList candidate: recallResult){
            int score = minDistance(query, candidate.getTitle()) + minDistance(query, candidate.getAuthor()) + minDistance(query, candidate.getIsbn());
            candidateSet.put(candidate, score);
        }
        List<Map.Entry<BookList, Integer>> list = new ArrayList<>(candidateSet.entrySet());
        list.sort(Map.Entry.comparingByValue());
        List<BookList> rankResult = new ArrayList<>();
        list.forEach(entry->rankResult.add(entry.getKey()));
        return rankResult;
    }

    @Override
    public String checkstatus(int userID) {
        int count = mapper.getRecord(new String[]{"processing"},userID).size();
        if(count >10){
            return "Check out books up to 10!";
        }
        if(mapper.getRecord(new String[]{"overdue"},userID).size() != 0){
            return "Have overdue book!";
        }
        if(mapper.getRecord(new String[]{"baned"},userID).size() != 0){
            return "Baned by admin";
        }
        return null;
    }
    public String getStatus(int userID) {
        int count = mapper.getRecord(new String[]{"processing"},userID).size();
        if(count >10){
            return "0";
        }
        if(mapper.getRecord(new String[]{"overdue"},userID).size() != 0){
            return "1";
        }
        if(mapper.getRecord(new String[]{"baned"},userID).size() != 0){
            return "2";
        }
        return "-1";
    }


    @Override
    public List<Book> searchSingleBook(String query) {
        return mapper.searchSingleBook(query);
    }

    @Override
    public List<Borrow> findoverdueBook(String status) {
        return mapper.getoverdueBook(status);
    }

    @Override
    public boolean returnBook(int BookID) {
//        BorrowDetails borrowDetails =mapper.findbookDetails(BookID,UserID);
        return mapper.updatebookDetails("done",BookID);
    }

    @Override
    public Book findbookbyID(int BookID) {
        return mapper.findBookbyID(BookID);
    }
}
