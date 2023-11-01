package com.example.demo.dao;

import com.example.demo.model.dataobject.BookDO;
import com.example.demo.model.dataobject.BookDetailDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author firesect
 * @create 2023/11/1
 */
@Mapper
public interface BookDAO {
    Integer addBook(BookDO bookDO);
    Integer addBookDetail(BookDetailDO bookDetailDO);
    Integer updateBook(BookDO bookDO);
    Integer updateBookDetail(BookDetailDO bookDetailDO);
}
