package com.example.demo.controller;

import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("/login")
    public String login(
            @RequestParam("email1") String useremail,
            @RequestParam("password") String password,
            Model model, HttpServletRequest request) {
        String username = usersService.userLogin(useremail,password);
        if(username !=null){
            request.getSession().setAttribute("User",username);
            return "index";
        }else{
            model.addAttribute("msg","Wrong password or Email not registered.");
            return "user/login";
        }
    }
}
