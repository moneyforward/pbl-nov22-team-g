package com.example.demo.controller;

import com.example.demo.pojo.Admin;
import com.example.demo.pojo.Users;
import com.example.demo.service.AdminService;
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
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/login")
    public String login(
            @RequestParam("nickname") String nickname,
            @RequestParam("password") String password,
            Model model, HttpServletRequest request, HttpServletResponse response) {
        Admin admin = adminService.adminLogin(nickname,password);
        if(admin !=null){
            request.getSession().setAttribute("adminid", admin.getAdminID());
            // need overdue status for session & cookie

            Cookie usernameCookie = new Cookie("adminname", admin.getNickname());
            response.addCookie(usernameCookie);
            return "admin/console";
        }else{
            model.addAttribute("msg","Wrong password or nickname not registered.");
            return "admin/login";
        }
    }
    @RequestMapping("/checkLogin")
    @ResponseBody
    public boolean checkLogin(HttpSession session){
        return session.getAttribute("adminid") != null;
    }

}
