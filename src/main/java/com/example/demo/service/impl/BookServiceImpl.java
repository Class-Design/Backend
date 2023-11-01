package com.example.demo.service.impl;

import com.example.demo.dao.BookDAO;
import com.example.demo.model.dataobject.BookDO;
import com.example.demo.model.dataobject.BookDetailDO;
import com.example.demo.model.dto.BookDTO;
import com.example.demo.model.pojo.Result;
import com.example.demo.service.BookService;
import com.example.demo.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
public class BookServiceImpl implements BookService {
    @Autowired
    BookDAO bookDAO;
    @Override
    public Result<Integer> addBook(BookDTO bookDTO) {
        Result<Integer> result=new Result<>();
        BookDO bookDO=bookDTO.toDO();
        bookDO.setBookId(UUIDUtils.getUUID());
        bookDAO.addBook(bookDO);
        // 存在多少库存就要在detail中创建多少书
        // 以此记录每本书的独立状态
        for (int i=bookDTO.getReserve();i>0;i--){
            addBookDetail(bookDO.getBookId());
        }
        result.setSuccess(true);
        result.setCode("200");
        result.setData(bookDTO.getReserve());
        return null;
    }

    @Override
    public Integer addBookDetail(String bookId) {
        BookDetailDO bookDetailDO=new BookDetailDO();
        bookDetailDO.setBookId(bookId);
        bookDetailDO.setDetailId(UUIDUtils.getUUID());
        bookDetailDO.setStatus(1);
        bookDetailDO.setRemark("在库中");
        return bookDAO.addBookDetail(bookDetailDO);
    }

    @Override
    public Result<Integer> updateBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public Result<Integer> updateBookDetail(String detailId) {
        return null;
    }
}
