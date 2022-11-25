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

class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getBookList() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/bookList"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

//    @Test
//    void getBook() throws Exception {
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/bookDetail"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }

//    @Test
//    void addReadPlan() throws Exception {
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/addReadPlan"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//
//    @Test
//    void getReadPlan() throws Exception {
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/getReadPlans"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }

//    @Test
//    void deleteReadPlan() throws Exception {
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/deleteReadPlan"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//
//    @Test
//    void reserveBook() throws Exception {
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/reserveBook"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//
//    }

//    @Test
//    void getInProgress() throws Exception {
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/getInProgress"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }
//
//    @Test
//    void getHistory() throws Exception {
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/getHistory"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }

//    @Test
//    void returnBook() throws Exception {
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/returnbook")
//                        .accept(MediaType.APPLICATION_JSON).param("bookid","2"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//
//    }

    @Test
    void searchBook() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/searchBook")
                        .accept(MediaType.APPLICATION_JSON).param("query","1984"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

//    @Test
//    void readUserQRCode() {
//    }

    @Test
    void findOverdueBook() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/findOverdueBook"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}