package com.example.demo.model.dto;

import com.example.demo.model.dataobject.BookDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
@Data
public class BookDTO {
    String bookId;
    String name;
    String author;
    String publisher;
    Double price;
    Date publish;
    Integer status;
    Integer reserve;
    public BookDO toDO() {
        BookDO bookDO = new BookDO();
        BeanUtils.copyProperties(this, bookDO);
        return bookDO;
    }
}
