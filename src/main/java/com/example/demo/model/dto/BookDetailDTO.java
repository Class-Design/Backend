package com.example.demo.model.dto;

import com.example.demo.model.dataobject.BookDetailDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author fireinsect
 * @create 2023/11/2
 */
@Data
public class BookDetailDTO {
    String bookId;
    String detailId;
    Integer status;
    String remark;
    public BookDetailDO toDO() {
        BookDetailDO bookDetailDO = new BookDetailDO();
        BeanUtils.copyProperties(this, bookDetailDO);
        return bookDetailDO;
    }
}
