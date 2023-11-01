package com.example.demo.model.dataobject;

import lombok.Data;

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
    Double price;
    Date publish;
    Integer reserve;
}
