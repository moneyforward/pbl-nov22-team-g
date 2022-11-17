package com.example.demo.controller;

import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {
    @Autowired
    private UsersService usersService;
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String index1() {
        return "index";
    }
    @GetMapping("/login")
    public String login1() {
        return "user/login";
    }
    @RequestMapping("/login")
    public String login(
            @RequestParam("exampleInputEmail1") String useremail,
            @RequestParam("exampleInputPassword1") String password,
            Model model,HttpServletRequest request) {
        String username = usersService.userLogin(useremail,password);
        if(username !=null){
            request.getSession().setAttribute("User",username);
            return "index";
        }else{
            model.addAttribute("msg","Wrong password or Email not registered.");
            return "user/login";
        }

    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    @GetMapping("/profile")
    public String profile() {
        return "user/profile";
    }

    @GetMapping("/booklist")
    public String booklist() {
        return "books/booklist";
    }

    @GetMapping("/bookdetail")
    public String bookdetail() {
        return "books/bookdetail";
    }
}
