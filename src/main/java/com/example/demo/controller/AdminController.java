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
        if(admin !=null){
            request.getSession().setAttribute("adminid", admin.getAdminID());
            Cookie usernameCookie = new Cookie("Email", admin.getEmail());
            usernameCookie.setMaxAge(12000);
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
    public List<Book> searchsingleBook(@RequestParam("query")String query){
        return bookService.searchSingleBook(query);
    }
    @RequestMapping("/admin/findbookbyID")
    @ResponseBody
    public Book findbookbyID(@RequestParam("BookID") String bookID){
        return bookService.findbookbyID(Integer.parseInt(bookID));
    }
    @RequestMapping("/admin/editBook")
    public String editBook(@RequestParam("title")String title,
                           @RequestParam("author") String author,
                           @RequestParam("ISBN") String ISBN,
                           @RequestParam("BookID") int BookID,
                           Model model){
        if(bookService.editBook(BookID,title, author, ISBN)){
            return "admin/console";
        }else{
            model.addAttribute("msg","Add new book error");
            return "admin/console";
        }

    }
    @RequestMapping("/admin/addAdmin")
    @ResponseBody
    public String addAdmin(@RequestParam("email")String email,
                           Model model){
        String password =adminService.addAdmin(email);
        Cookie usernameCookie = new Cookie("initPass", password);
        usernameCookie.setMaxAge(12000);
        if(password!=null){
            model.addAttribute("msg","add success!,initial password is "+password);
            return "add success!,initial password is "+password;
        }else{
            model.addAttribute("msg","error!");
            return "Unexpect error!";
        }
    }
    @RequestMapping("/searchUser")
    public String searchUser(String userQuery,
                             Model model){
        Users user = adminService.findUser(userQuery);
        if(user == null){
            model.addAttribute("error","User does not exist.");
        }
        else{
            model.addAttribute("UserID",user.getUserID());
            model.addAttribute("Nickname",user.getNickname());
            model.addAttribute("Email",user.getEmail());
            model.addAttribute("userMsg", "Click to check user's reservation");

            String userStatus = bookService.checkstatus(user.getUserID());
            if(userStatus == null) {
                model.addAttribute("btnHtml",
                        "<button class=\"btn btn-danger\" onclick=\"banUser(" + user.getUserID() + ")\">Ban</button>");
            }else{
                model.addAttribute("btnHtml",
                        "<button class=\"btn btn-danger\" onclick=\"unBanUser(" + user.getUserID() + ")\">Active</button><p class=\"text-danger\">"+userStatus+"</p>");
            }
        }
        return "admin/console";
    }
    @RequestMapping("/admin/userinfo")
    @ResponseBody
    public List<BorrowDetails> userInfo(@RequestParam("UserID") int id){
        return bookService.getRecord(new String[]{"processing","pending","done"},id);
    }
    @RequestMapping("/admin/overduebook")
    @ResponseBody
    public List<Borrow> overduebook(){
        return bookService.findoverdueBook("overdue");
    }

    @RequestMapping("/banUser")
    public String banUser(String userCode){
        adminService.banUser(Integer.parseInt(userCode));
        return "admin/console";
    }

    @RequestMapping("/unBanUser")
    public String unBanUser(String userCode){
        adminService.unBanUser(Integer.parseInt(userCode));
        return "admin/console";
    }
}
