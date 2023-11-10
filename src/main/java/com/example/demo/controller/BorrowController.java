package com.example.demo.controller;

import com.example.demo.model.dataobject.MyBorrowDTO;
import com.example.demo.model.dto.BookDTO;
import com.example.demo.model.dto.BookDetailDTO;
import com.example.demo.model.pojo.Result;
import com.example.demo.service.BorrowService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Result borrow(@RequestBody BookDTO bookDTO, HttpServletRequest request){
        return borrowService.borrowBook(bookDTO,request);
    }
    @PostMapping("/borrow/return")
    public Result returnBook(@RequestBody MyBorrowDTO myBorrowDTO, HttpServletRequest request){
        return borrowService.returnBook(myBorrowDTO,request);
    }
    @PostMapping("/borrow/reserve")
    public Result reserve(@RequestBody BookDTO bookDTO, HttpServletRequest request){
        return borrowService.reserveBook(bookDTO,request);
    }
    @GetMapping("/borrow/myBorrow")
    public Result returnBook(HttpServletRequest request){
        return borrowService.myBorrow(request);
    }
}
