package com.example.demo.controller;

import com.example.demo.pojo.Users;
import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("/login")
    public String login(
            @RequestParam("email1") String useremail,
            @RequestParam("password") String password,
            Model model, HttpServletRequest request, HttpServletResponse response) {
        Users userInfo = usersService.userLogin(useremail,password);
        if(userInfo !=null){
            request.getSession().setAttribute("userid", userInfo.getUserID());
            // need overdue status for session & cookie

            Cookie usernameCookie = new Cookie("username", userInfo.getNickname());
            response.addCookie(usernameCookie);
            return "index";
        }else{
            model.addAttribute("msg","Wrong password or Email not registered.");
            return "user/login";
        }
    }

    @RequestMapping("/checkLogin")
    @ResponseBody
    public boolean checkLogin(HttpSession session){
        return session.getAttribute("userid") != null;
    }

    @RequestMapping("/signup")
    public String signup(
            @RequestParam("email") String useremail,
            @RequestParam("password") String password,
            @RequestParam("nickname") String nickname,
            Model model, HttpServletRequest request){
        String status= usersService.userSignup(useremail,password,nickname);
        if(status == null){
            request.getSession().setAttribute("User",nickname);
            return "user/login";
        }else{
            model.addAttribute("msg",status);
            return "user/signup";
        }
    }
}
