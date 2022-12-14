package com.example.demo.controller;

import com.example.demo.pojo.BookDetail;
import com.example.demo.pojo.BookList;
import com.example.demo.pojo.Borrow;
import com.example.demo.pojo.BorrowDetails;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String deleteReadPlan(String title, HttpSession session){
        bookService.deletePlan(title, Integer.parseInt(session.getAttribute("userid").toString()));
        return "";
    }

    @RequestMapping("/reserveBook")
    @ResponseBody
    public String reserveBook(String title, HttpSession session, Model model){
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
            return status;
        }
        if(bookId != -1) {
            bookService.reserveBook(bookId, Integer.parseInt(session.getAttribute("userid").toString()));
            return "success";
        }
        return "unexpected error!";
    }

    @RequestMapping("/getInProgress")
    @ResponseBody
    public List<BorrowDetails> getInProgress(HttpSession session){
        return bookService.getRecord(new String[]{"processing", "pending", "overdue"}, Integer.parseInt(session.getAttribute("userid").toString()));
    }

    @RequestMapping("/getHistory")
    @ResponseBody
    public List<BorrowDetails> getHistory(HttpSession session){
        return bookService.getRecord(new String[]{"done"}, Integer.parseInt(session.getAttribute("userid").toString()));
    }
    @RequestMapping("/returnBook")
    @ResponseBody
    public String returnBook(String bookID){
        bookService.returnBook(Integer.parseInt(bookID));
        return "";
    }

    @RequestMapping("/checkInBook")
    @ResponseBody
    public String checkInBook(String userCode){
        int userid = userCode.charAt(0) == 'u' ?(Integer.parseInt(userCode.substring(1))-414)/42:Integer.parseInt(userCode);
        String status = bookService.checkstatus(userid);
        if(status!=null) return status;
        bookService.checkInBook(userid);
        return "check in success!";
    }

    @RequestMapping("/searchBook")
    @ResponseBody
    public List<BookList> searchBook(@RequestParam("query") String query){
        return bookService.searchBook(query);
    }

    @RequestMapping("/readUserQRCode")
    @ResponseBody
    public List<BorrowDetails> readUserQRCode(String userCode){
        int userid = userCode.charAt(0) == 'u' ?(Integer.parseInt(userCode.substring(1))-414)/42:Integer.parseInt(userCode);
        return bookService.getRecord(new String[]{"pending"}, userid);
    }

    @RequestMapping("findOverdueBook")
    @ResponseBody
    public List<Borrow> findOverdueBook(){
        return bookService.findoverdueBook("overdue");
    }
}
