package com.example.demo.pojo;


import lombok.Data;
import org.apache.catalina.authenticator.SavedRequest;

@Data
public class Superusers {
    private int ID;
    private String Username;
    private String Password;
}
