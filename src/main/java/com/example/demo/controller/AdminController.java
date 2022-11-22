package com.example.demo.controller;

import com.example.demo.pojo.*;
import com.example.demo.service.AdminService;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private BookService bookService;
    @RequestMapping("/admin")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model, HttpServletRequest request, HttpServletResponse response) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String encodepassword = bCryptPasswordEncoder.encode(password);
        Admin admin = adminService.adminLogin(email,password);
        System.out.println(admin);
        if(admin !=null){
            request.getSession().setAttribute("adminid", admin.getAdminID());
            Cookie usernameCookie = new Cookie("Email", admin.getEmail());
            response.addCookie(usernameCookie);
            return "admin/console";
        }else{
            model.addAttribute("msg","Wrong password or email not registered.");
            return "admin/login";
        }
    }

    @RequestMapping("/admin/addbook")
    public String addBook(@RequestParam("title")String title,
                          @RequestParam("author") String author,
                          @RequestParam("ISBN") String ISBN,
                          Model model){
        if(bookService.addnewBook(title, author, ISBN)){
            return "books/booklist";
        }else{
            model.addAttribute("msg","Add new book error");
            return "admin/console";
        }

    }
    //need a button to change the book details.
    @RequestMapping("/admin/searchsinglebook")
    @ResponseBody
    public List<BookList> searchsingleBook(@RequestParam("query")String query){
        return bookService.searchSingleBook(query);
    }
    @RequestMapping("/admin/findbookbyID")
    public Book findbookbyID(@RequestParam("BookID") int bookID){
        return bookService.findbookbyID(bookID);
    }
    @RequestMapping("/admin/editbook")
    public String editBook(@RequestParam("title")String title,
                           @RequestParam("author") String author,
                           @RequestParam("ISBN") String ISBN,
                           @RequestParam("BookID") int BookID,
                           Model model){
        if(bookService.editBook(BookID,title, author, ISBN)){
            return "books/booklist";
        }else{
            model.addAttribute("msg","Add new book error");
            return "admin/console";
        }

    }
    @RequestMapping("/admin/addAdmin")
    public String addAdmin(@RequestParam("email")String email,
                           Model model){
        String password =adminService.addAdmin(email);
        if(password!=null){
            model.addAttribute("msg","add success!,initial password is "+password);
        }else{
            model.addAttribute("msg","error!");
        }
        return "admin/console";

    }
    @RequestMapping("/admin/searchUser")
    public String searchUser(@RequestParam("key")String key,
                             Model model){
        Users user = adminService.findUser(key);
        if(user == null){
            model.addAttribute("error","User does not exist.");
            return "admin/console";
        }
        else{
            model.addAttribute("UserID",user.getUserID());
            model.addAttribute("Nickname",user.getNickname());
            model.addAttribute("Email",user.getEmail());
            return "admin/console";
        }
    }
    @RequestMapping("/admin/userinfo")
    @ResponseBody
    public List<BorrowDetails> userInfo(@RequestParam("UserID") int id){
        return bookService.getRecord(new String[]{"processing","pending","done"},id);
    }
    @RequestMapping("/admin/overduebook")
    @ResponseBody
    public List<BorrowDetails> overduebook(){
        return bookService.findoverdueBook("overdue");
    }









}
