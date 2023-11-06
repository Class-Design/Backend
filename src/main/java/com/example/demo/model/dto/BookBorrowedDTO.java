package com.example.demo.model.dto;

import lombok.Data;

/**
 * @author fireinsect
 * @create 2023/11/5
 */
@Data
public class BookBorrowedDTO {
    String detailId;
    BookDTO bookInfo;
}
