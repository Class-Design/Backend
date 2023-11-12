package com.example.demo.model.pojo;

import com.example.demo.model.dataobject.BookDO;
import com.example.demo.model.dataobject.UserDetailDO;
import lombok.Data;

/**
 * @author fireinsect
 * @create 2023/11/11
 */
@Data
public class BorrowGeneral {
    String detailId;
    UserDetailDO userInfo;
    BookDO bookInfo;
}
