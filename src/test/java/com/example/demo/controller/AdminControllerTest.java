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
class AdminControllerTest {
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {

    }

    @Test
    void login() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin")
                        .accept(MediaType.APPLICATION_JSON).param("email","admin@mf.com").param("password","1234567"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void loginnull() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin")
                        .accept(MediaType.APPLICATION_JSON).param("email","admin@mf.com").param("password","123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void addBook() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin/addbook")
                        .accept(MediaType.APPLICATION_JSON).param("title","1234").param("author","wo").param("ISBN","ISBN123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }
    @Test
    void addBooknull() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin/addbook")
                        .accept(MediaType.APPLICATION_JSON).param("title","").param("author","wo").param("ISBN","ISBN123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    void searchsingleBook() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin/searchsinglebook")
                            .accept(MediaType.APPLICATION_JSON).param("query","overdue"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void findbookbyID() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin/findbookbyID")
                        .accept(MediaType.APPLICATION_JSON).param("BookID","333"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void editBook() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin/editBook")
                        .accept(MediaType.APPLICATION_JSON).param("title","1996").param("author","wo").param("ISBN","ISBN123").param("BookID","1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }
//    @Test
//    void editBooknull() throws Exception {
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin/editBook")
//                        .accept(MediaType.APPLICATION_JSON).param("title","1996").param("author","wo").param("ISBN","ISBN123").param("BookID","777"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//
//    }


    @Test
    void addAdmin() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin/addAdmin")
                        .accept(MediaType.APPLICATION_JSON).param("email","3333@mf.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void searchUser() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin/searchUser")
                        .accept(MediaType.APPLICATION_JSON).param("key","hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void searchUserNull() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin/searchUser")
                        .accept(MediaType.APPLICATION_JSON).param("key","xxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    void userInfo() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin/userinfo")
                        .accept(MediaType.APPLICATION_JSON).param("UserID","3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void overduebook() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/admin/overduebook"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

//    @Test
//    void banUser() throws Exception {
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/banUser")
//                        .accept(MediaType.APPLICATION_JSON).param("userCode","3"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//
//    }
//
//    @Test
//    void unBanUser() throws Exception {
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/unBanUser"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
}