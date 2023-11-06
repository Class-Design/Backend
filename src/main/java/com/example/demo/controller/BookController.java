package com.example.demo.controller;

import com.example.demo.model.dto.BookDTO;
import com.example.demo.model.dto.BookDetailDTO;
import com.example.demo.model.pojo.Result;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
@RestController
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/book/add")
    public Result addBook(BookDTO bookDTO){
        return bookService.addBook(bookDTO);
    }
    @PostMapping("/book/update")
    public Result updateBook(BookDTO bookDTO){
        return bookService.updateBook(bookDTO);
    }
    @PostMapping("/bookDetail/update")
    public Result updateBookDetail(BookDetailDTO bookDetailDTO){
        return bookService.updateBookDetail(bookDetailDTO);
    }
}
