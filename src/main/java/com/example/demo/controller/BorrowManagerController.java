package com.example.demo.controller;

import com.example.demo.model.dto.BorrowDTO;
import com.example.demo.model.pojo.Result;
import com.example.demo.service.BorrowManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fireinsect
 * @create 2023/11/11
 */
@RestController
public class BorrowManagerController {
    @Autowired
    BorrowManagerService borrowManagerService;
    @GetMapping("/borrow-manage/list")
    public Result getList(BorrowDTO borrowDTO){
        return borrowManagerService.getList(borrowDTO);
    }
}
