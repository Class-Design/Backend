package com.example.demo.service;

import com.example.demo.model.dataobject.BookDO;
import com.example.demo.model.dto.BookDTO;
import com.example.demo.model.pojo.Result;
import org.springframework.data.relational.core.sql.In;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
public interface BookService {
    /**
     * 创建书籍
     * @param bookDTO
     * @return
     */
    Result<Integer> addBook(BookDTO bookDTO);

    /**
     * 创建某具体书籍
     * @param bookDO
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
     * @param detailId
     * @return
     */
    Result<Integer> updateBookDetail(String detailId);
}
