package com.example.demo.controller;

import com.example.demo.model.dto.BookDTO;
import com.example.demo.model.dto.BookDetailDTO;
import com.example.demo.model.pojo.Result;
import com.example.demo.service.BorrowService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fireinsect
 * @create 2023/11/3
 */
@RestController
public class BorrowController {
    @Autowired
    BorrowService borrowService;
    @PostMapping("/borrow/borrow")
    public Result borrow(BookDTO bookDTO, HttpServletRequest request){
        return borrowService.borrowBook(bookDTO,request);
    }
    @PostMapping("/borrow/return")
    public Result returnBook(BookDetailDTO bookDetailDTO, HttpServletRequest request){
        return borrowService.returnBook(bookDetailDTO,request);
    }
}
