package com.example.demo.model.dataobject;

import com.example.demo.model.dto.BookDTO;
import com.example.demo.model.dto.BookDetailDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
@Data
public class BookDetailDO {
    Integer id;
    String bookId;
    String detailId;
    Integer status;
    String remark;
    public BookDetailDTO toDTO() {
        BookDetailDTO bookDetailDTO = new BookDetailDTO();
        BeanUtils.copyProperties(this, bookDetailDTO);
        return bookDetailDTO;
    }
}
