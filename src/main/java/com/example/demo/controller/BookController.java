package com.example.demo.controller;

import com.example.demo.pojo.BookDetail;
import com.example.demo.pojo.BookList;
import com.example.demo.pojo.BorrowDetails;
import com.example.demo.service.BookService;
import com.example.demo.service.SuperusersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/bookList")
    @ResponseBody
    public List<BookList> getBookList(){
        return bookService.getBookList();
    }

    @RequestMapping("/bookDetail")
    @ResponseBody
    public HashMap<String, String> getBook(String title){
        List<BookDetail> bookDetailStatuses = bookService.getBookDetail(title);

        HashMap<String, String> bookDetails = bookDetailStatuses.get(0).buildPublicInfo();
        for(BookDetail bookDetail : bookDetailStatuses){
            String status = bookDetail.getStatus() != null? bookDetail.getStatus():"avail";

            bookDetails.put(status, bookDetail.getStatusCount()+"");
        }

        return bookDetails;
    }

    @RequestMapping("/addReadPlan")
    @ResponseBody
    public String addReadPlan(String title, HttpSession session){
        bookService.addReadPlan(title, Integer.parseInt(session.getAttribute("userid").toString()));
        return "";
    }
    @RequestMapping("/getReadPlans")
    @ResponseBody
    public List<BookDetail> getReadPlan(HttpSession session){
        return bookService.getReadPlan(Integer.parseInt(session.getAttribute("userid").toString()));
    }
    @RequestMapping("/deleteReadPlan")
    @ResponseBody
    public boolean deleteReadPlan(String title, HttpSession session){
        bookService.deletePlan(title, Integer.parseInt(session.getAttribute("userid").toString()));
        return true;
    }

    @RequestMapping("/reserveBook")
    @ResponseBody
    public boolean reserveBook(String title, HttpSession session, Model model){
        List<BookDetail> bookDetailStatuses = bookService.getBookDetail(title);
        int bookId = -1;
        for(BookDetail bookDetail : bookDetailStatuses){
            if(bookDetail.getStatus()!=null) continue;
            bookId = bookDetail.getBookID();
            break;
        }
        String status = bookService.checkstatus(Integer.parseInt(session.getAttribute("userid").toString()));
        if(status != null){
            model.addAttribute("msg", status);
            return false;
        }
        if(bookId != -1) {
            bookService.reserveBook(bookId, Integer.parseInt(session.getAttribute("userid").toString()));
            return true;
        }
        return false;
    }

    @RequestMapping("/getInProgress")
    @ResponseBody
    public List<BorrowDetails> getInProgress(HttpSession session){
        return bookService.getRecord(new String[]{"processing", "pending"}, Integer.parseInt(session.getAttribute("userid").toString()));
    }

    @RequestMapping("/getHistory")
    @ResponseBody
    public List<BorrowDetails> getHistory(HttpSession session){
        return bookService.getRecord(new String[]{"done"}, Integer.parseInt(session.getAttribute("userid").toString()));
    }
    @RequestMapping("/returnbook")
    public boolean returnBook(@RequestParam("bookid")int bookid,
                             HttpSession session){
        return bookService.returnBook(bookid);
    }

    @RequestMapping("/searchBook")
    @ResponseBody
    public List<BookList> searchBook(@RequestParam("query") String query){
        return bookService.searchBook(query);
    }

    @RequestMapping("/readUserQRCode")
    @ResponseBody
    public List<BorrowDetails> readUserQRCode(String userCode){
        int userid = (Integer.parseInt(userCode)-414)/42;
        return bookService.getRecord(new String[]{"pending"}, userid);
    }
}
