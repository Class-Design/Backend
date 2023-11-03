package com.example.demo.dao;

import com.example.demo.model.dataobject.ReserveDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/3
 */
@Mapper
public interface ReserveDAO {
    /**
     * 未完成
     * 完成预定表添加
     */
    Integer add(ReserveDO reserveDO);

    /**
     * 未完成
     * 完成预定表删除
     * 通过内部两个参数选择
     * @param reserveDO
     * @return
     */
    Integer delete(ReserveDO reserveDO);

    /**
     * 未完成
     * 通过bookId搜索到全部预定信息
     * @param bookId
     * @return
     */
    List<ReserveDO> searchByBookId(@Param("bookId")String bookId);
}
