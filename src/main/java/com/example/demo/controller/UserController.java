package com.example.demo.controller;

import com.example.demo.pojo.Users;
import com.example.demo.service.BookService;
import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private BookService bookService;

    @RequestMapping("/login")
    public String login(
            @RequestParam("email1") String useremail,
            @RequestParam("password") String password,
            Model model, HttpServletRequest request, HttpServletResponse response) {
        Users userInfo = usersService.userLogin(useremail,password);
        if(userInfo !=null){
            request.getSession().setAttribute("userid", userInfo.getUserID());
            // need overdue status for session & cookie
            Cookie statusCookie = new Cookie("status", bookService.getStatus(userInfo.getUserID()));
            statusCookie.setMaxAge(12000);
            response.addCookie(statusCookie);

            Cookie usernameCookie = new Cookie("username", userInfo.getNickname());
            usernameCookie.setMaxAge(12000);
            response.addCookie(usernameCookie);
            return "index";
        }else{
            model.addAttribute("msg","Wrong password or Email not registered.");
            model.addAttribute("valid", "form-control is-invalid");
            return "user/login";
        }
    }

    @RequestMapping("/checkLogin")
    @ResponseBody
    public boolean checkLogin(HttpSession session){
        if(session.getAttribute("userid") != null){
            return true;
        }else{
            session.invalidate();
            return false;
        }
    }
    @RequestMapping("/getUserId")
    @ResponseBody
    public String getUserId(HttpSession session){
        return "u"+(Integer.parseInt(session.getAttribute("userid").toString())*42+414);
    }

    @RequestMapping("/signup")
    public String signup(
            @RequestParam("email") String useremail,
            @RequestParam("password") String password,
            @RequestParam("nickname") String nickname,
            Model model){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodepassword = bCryptPasswordEncoder.encode(password);
        String status= usersService.userSignup(useremail,encodepassword,nickname);
        if(status == null){
            return "user/login";
        }else{
            model.addAttribute("msg",status);
            return "user/signup";
        }
    }
    @GetMapping("/profile")
    public String getProfile(Model model,HttpServletRequest request){
        HttpSession session=request.getSession(false);
        if(session == null){
            return "user/login";
        }
        int id = (int) session.getAttribute("userid");
        Users users = usersService.getUserPofile(id);
        model.addAttribute("email",users.getEmail());
        model.addAttribute("nickname",users.getNickname());
        return "user/profile";
    }
    @RequestMapping("/profile")
    public String userupdate(
            @RequestParam("email") String useremail,
            @RequestParam("password") String password,
            @RequestParam("nickname") String nickname,
            Model model, HttpServletRequest request
    ){
        HttpSession session=request.getSession(false);
        if(session == null){
            return "user/login";
        }
        int id = (int) session.getAttribute("userid");
        String status = usersService.userUpdate(id,useremail,password,nickname);
        if(status == null){
            model.addAttribute("msg","The information has changed and needs to be logged again.");
            return "user/login";
        }else{
            model.addAttribute("msg",status);
            return"user/profile";
        }
    }
}
