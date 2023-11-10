package com.example.demo.controller;

import com.example.demo.model.dto.BookDTO;
import com.example.demo.model.pojo.Result;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fireinsect
 * @create 2023/11/8
 */
@RestController
public class BookManageController {
    @Autowired
    BookService bookService;

    /**
     * 和BookControl一样，但传入页面不一样，想想还是独立出来了
     * @param bookDTO
     * @return
     */
    @GetMapping("/book-manage/list")
    public Result list(BookDTO bookDTO){
        return bookService.getList(bookDTO);
    }
}
