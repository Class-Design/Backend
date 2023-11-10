package com.example.demo.dao;

import com.example.demo.model.dataobject.BookDetailDO;
import com.example.demo.model.dataobject.BorrowDO;
import com.example.demo.model.dto.BookDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/2
 */
@Mapper
public interface BorrowDAO {
    /**
     * 未完成
     * 判断是否存在库存,查询不在bookdetail表中bookId确定情况下，不在borrows表中存在的书
     * 注意使用 limit 1
     * 查询detail时记得判断status为1
     * @param bookId
     * @return
     */
    BookDetailDO bookReserve(String bookId);

    /**
     * 未完成
     * 根据传入直接输入数据
     * @param borrowDO
     * @return
     */
    Integer borrow(BorrowDO borrowDO);

    /**
     * 未完成
     * 根据传入直接删除数据，注意判断条件，需要bookId,detailId,userId都满足
     * @param borrowDO
     * @return
     */
    Integer returBook(BorrowDO borrowDO);

    /**
     * 通过userId搜索借阅的书
     * @param userId
     * @return
     */
    List<BorrowDO> searchByUserId(@Param("userId")String userId);
}
