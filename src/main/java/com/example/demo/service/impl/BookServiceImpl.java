package com.example.demo.service.impl;

import com.example.demo.dao.BookDAO;
import com.example.demo.dao.BorrowDAO;
import com.example.demo.model.dataobject.BookDO;
import com.example.demo.model.dataobject.BookDetailDO;
import com.example.demo.model.dto.BookDTO;
import com.example.demo.model.dto.BookDetailDTO;
import com.example.demo.model.pojo.Result;
import com.example.demo.service.BookService;
import com.example.demo.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDAO bookDAO;
    @Autowired
    BorrowDAO borrowDAO;
    @Override
    public Result<List<BookDTO>> getList(BookDTO bookDTO) {
        Result<List<BookDTO>> result=new Result<>();
        List<BookDTO> data=new ArrayList<>();
        List<BookDO> bookDOS=bookDAO.list(bookDTO);
        Iterator<BookDO> iterator = bookDOS.iterator();
        while(iterator.hasNext()){
            BookDO bookDO=iterator.next();
            BookDetailDO bookDetailDO=borrowDAO.bookReserve(bookDO.getBookId());
            BookDTO bookDTO1=bookDO.toDTO();
            bookDTO1.setStatus(bookDetailDO==null?0:1);
            if (bookDTO.getStatus()!=null){
                if ((bookDTO.getStatus()==0&&bookDetailDO==null)||(bookDTO.getStatus()==1&&bookDetailDO!=null)){
                    data.add(bookDTO1);
                }
            }else{
                data.add(bookDTO1);
            }
        }
        result.setData(data);
        result.setCode("200");
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<BookDetailDTO>> getDetailList(String bookId) {
        Result<List<BookDetailDTO>> result=new Result<>();
        List<BookDetailDO> bookDetailDOS= bookDAO.listDetail(bookId);
        List<BookDetailDTO> data= new ArrayList<>();
        for (BookDetailDO bookDetailDO:bookDetailDOS){
            data.add(bookDetailDO.toDTO());
        }
        result.setCode("200");
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

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
        return result;
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
        System.out.println(bookDTO);
        Result<Integer> result=new Result<>();
        if (StringUtils.isEmpty(bookDTO.getBookId())){
            result.setCode("400");
            result.setData(0);
            result.setMessage("未选择正确的书籍");
            result.setSuccess(false);
            return result;
        }
        BookDO bookDO=bookDTO.toDO();
        Integer hasChange=bookDAO.updateBook(bookDTO.toDO());
        result.setCode("200");
        result.setData(hasChange);
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Integer> updateBookDetail(BookDetailDTO bookDetailDTO) {
        Result<Integer> result=new Result<>();
        if (StringUtils.isEmpty(bookDetailDTO.getDetailId())){
            result.setCode("400");
            result.setData(0);
            result.setMessage("未选择正确的书本");
            result.setSuccess(false);
            return result;
        }
        Integer hasChange=bookDAO.updateBookDetail(bookDetailDTO.toDO());
        result.setCode("200");
        result.setData(hasChange);
        result.setSuccess(true);
        return result;
    }
}
