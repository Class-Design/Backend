package com.example.demo.model.dataobject;

import com.example.demo.model.dto.BookDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
@Data
public class BookDO {
    Integer id;
    String bookId;
    String name;
    String author;
    String publisher;
    Double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date publish;
    Integer reserve;
    public BookDTO toDTO() {
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(this, bookDTO);
        return bookDTO;
    }
}
