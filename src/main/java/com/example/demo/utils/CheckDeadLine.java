package com.example.demo.utils;

import com.example.demo.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CheckDeadLine {
    @Autowired
    private BookDao bookDao;

    @Scheduled(fixedRate = 3600000)
    public void CheckDeadLine(){
        System.out.println(LocalDateTime.now()+" check the deadline...");
        bookDao.checkProcessingExpire();
        bookDao.checkPendingExpire();
    }
}
