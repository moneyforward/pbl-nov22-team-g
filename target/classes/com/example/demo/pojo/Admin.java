package com.example.demo.pojo;


import lombok.Data;

@Data
public class Admin {
    private String AdminID;
    private String Email;
    private String Password;

    public Admin(String email, String password) {
        Email = email;
        Password = password;
    }
}
//public record Admin(String AdminID,String Nickname,String Password){};