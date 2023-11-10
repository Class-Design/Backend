package com.example.demo.service;

import com.example.demo.model.dataobject.BookDO;
import com.example.demo.model.dataobject.BookDetailDO;
import com.example.demo.model.dataobject.MyBorrowDTO;
import com.example.demo.model.dto.BookDTO;
import com.example.demo.model.dto.BookDetailDTO;
import com.example.demo.model.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/2
 */
public interface BorrowService {
    Result<BookDetailDO> borrowBook(BookDTO bookDTO, HttpServletRequest request);
    Result returnBook(MyBorrowDTO myBorrowDTO,HttpServletRequest request);

    Result<List<MyBorrowDTO>> myBorrow(HttpServletRequest request);

    Result reserveBook(BookDTO bookDTO, HttpServletRequest request);
}
