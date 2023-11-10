package com.example.demo.service;

import com.example.demo.model.dataobject.BookDO;
import com.example.demo.model.dto.BookDTO;
import com.example.demo.model.dto.BookDetailDTO;
import com.example.demo.model.pojo.Result;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
public interface BookService {
    Result<List<BookDTO>> getList(BookDTO bookDTO);
    Result<List<BookDetailDTO>> getDetailList(String bookId);

    /**
     * 创建书籍
     * @param bookDTO
     * @return
     */
    Result<Integer> addBook(BookDTO bookDTO);

    /**
     * 创建某具体书籍
     * @param bookId
     * @return
     */
    Integer addBookDetail(String bookId);

    /**
     * 更新书籍
     * @param bookDTO
     * @return
     */
    Result<Integer> updateBook(BookDTO bookDTO);

    /**
     * 更新具体书籍
     * @param bookDetailDTO
     * @return
     */
    Result<Integer> updateBookDetail(BookDetailDTO bookDetailDTO);
}
