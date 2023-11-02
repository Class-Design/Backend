package com.example.demo.dao;

import com.example.demo.model.dataobject.BookDO;
import com.example.demo.model.dataobject.BookDetailDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author firesect
 * @create 2023/11/1
 */

/**
 * 图书模糊查询功能未写
 */
@Mapper
public interface BookDAO {
    /**
     * 未完成sql
     * 根据传入直接添加数据，注意自增主键
     * @param bookDO
     * @return
     */
    Integer addBook(BookDO bookDO);

    /**
     * 未完成
     * 根据传入直接添加数据，注意自增主键
     * @param bookDetailDO
     * @return
     */
    Integer addBookDetail(BookDetailDO bookDetailDO);

    /**
     * 未完成
     * 根据传入更新数据，注意对每个字段进行独立判断
     * @param bookDO
     * @return
     */
    Integer updateBook(BookDO bookDO);

    /**
     * 未完成
     * 根据传入更新数据，注意对每个字段进行判断
     * @param bookDetailDO
     * @return
     */
    Integer updateBookDetail(BookDetailDO bookDetailDO);
}
