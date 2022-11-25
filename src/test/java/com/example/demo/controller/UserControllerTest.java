package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @BeforeEach
    void setUp() {

    }

    @Test
    void login() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/login")
                        .accept(MediaType.APPLICATION_JSON).param("email1","111@mf.com").param("password","abcd1234!"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void loginnull() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/login")
                        .accept(MediaType.APPLICATION_JSON).param("email1","123@mf.com").param("password","a"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void checkLogin() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/checkLogin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

//    @Test
//    void getUserId() throws Exception {
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/getUserId"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//
//    }

    @Test
    void signup() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/signup")
                        .accept(MediaType.APPLICATION_JSON).param("email","123@mf.com").param("password","a").param("nickname","xxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void getProfile() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/profile"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void userupdate() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/profile")
                        .accept(MediaType.APPLICATION_JSON).param("email","123@mf.com").param("password","a").param("nickname","xxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}