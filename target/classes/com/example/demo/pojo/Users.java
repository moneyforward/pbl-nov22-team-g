package com.example.demo.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class Users {
// Users id, name ,e-mail,
//    private String Email;
    @NotNull
    private String Nickname;
    private int UserID;
    @NotNull
//    @Length(min = 8,message = " must be more than eight letters")
    @Pattern(regexp = "^(?![^a-zA-z]+$)(?!\\D+$)(?![a-zA-Z0-9]+$).{8,}$",
            message = "Passwords must be more than eight letters and include numbers or symbols and at least one character.")
    private String Password;
    private String Email;


    public Users(String Nickname, String Password, String Email, int UserID){
        this.UserID = UserID;
        this.Nickname = Nickname;
        this.Password = Password;
        this.Email = Email;
    }
    public Users(String Nickname, String Password, String Email){
//        this.UserID = UserID;
        this.Nickname = Nickname;
        this.Password = Password;
        this.Email = Email;
    }
    public String toString(){
        return "User{"+
                "id="+UserID+
                ", passward="+Password+
                ", nickname="+Nickname+
                "Email="+Email+"}";
    }
}
