package com.example.demo.pojo;


import lombok.Data;

@Data
public class Admin {
    private String AdminID;
    private String Nickname;
    private String Password;

    public Admin(String nickname, String password) {
        Nickname = nickname;
        Password = password;
    }
}
//public record Admin(String AdminID,String Nickname,String Password){};