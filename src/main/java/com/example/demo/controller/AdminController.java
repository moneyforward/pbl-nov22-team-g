package com.example.demo.controller;

import com.example.demo.pojo.Admin;
import com.example.demo.pojo.BookList;
import com.example.demo.service.AdminService;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestParam("nickname") String nickname,
            @RequestParam("password") String password,
            Model model, HttpServletRequest request, HttpServletResponse response) {
        Admin admin = adminService.adminLogin(nickname,password);
        if(admin !=null){
            request.getSession().setAttribute("adminid", admin.getAdminID());
            Cookie usernameCookie = new Cookie("adminname", admin.getNickname());
            response.addCookie(usernameCookie);
            return "console";
        }else{
            model.addAttribute("msg","Wrong password or nickname not registered.");
            return "admin/login";
        }
    }
//    @RequestMapping("/checkLogin")
//    @ResponseBody
//    public boolean checkLogin(HttpSession session){
//        return session.getAttribute("adminid") != null;
//    }

    @RequestMapping("/admin/addbook")
    public String addBook(@RequestParam("title")String title,
                          @RequestParam("author") String author,
                          @RequestParam("ISBN") String ISBN,
                          Model model){
        if(bookService.addnewBook(title, author, ISBN)){
            return "/admin/booklist";
        }else{
            model.addAttribute("msg","Add new book error");
            return "/admin/addbook";
        }

    }
    //need a button to change the book details.
    @GetMapping("/amin/booklist")
    public List<BookList> bookList(){return bookService.getBookList();}
    @RequestMapping("/admin/editbook")
    public String editBook(@RequestParam("title")String title,
                           @RequestParam("author") String author,
                           @RequestParam("ISBN") String ISBN,
                           Model model){
        if(bookService.addnewBook(title, author, ISBN)){
            return "/admin/booklist";
        }else{
            model.addAttribute("msg","Add new book error");
            return "/admin/addbook";
        }

    }
    @RequestMapping("/admin/addAdmin")
    public String addAdmin(@RequestParam("nickname")String nickname,
                           @RequestParam("password") String password,
                           Model model){
        if(adminService.addAdmin(nickname,password)){
            model.addAttribute("msg","add success!");
        }else{
            model.addAttribute("msg","error!");
        }
        return "/admin/addAdmin";

    }






}
