package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String index1() {
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

//    @GetMapping("/profile")
//    public String profile() {
//        return "user/profile";
//    }

    @GetMapping("/booklist")
    public String bookList() {
        return "books/booklist";
    }

    @GetMapping("/bookdetail")
    public String bookDetail() {
        return "books/bookdetail";
    }

    @GetMapping("/admin")
    public String adminLogin(){return "admin/login";}

    @GetMapping("/console")
    public String adminProfile(){return "admin/console";}

    @GetMapping("/qr")
    public String bookQR(){return "bookQRGen";}
}
