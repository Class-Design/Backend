package com.example.demo.service;

import com.example.demo.model.dataobject.ReserveDO;
import com.example.demo.model.dto.ReserveDTO;
import com.example.demo.model.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author fireinsect
 * @create 2023/11/3
 */
public interface ReserveService {
    /**
     * 预定借阅
     */
    Result reserve(ReserveDTO reserveDTO, HttpServletRequest request);

    /**
     * 通知预定人
     * @param bookId
     * @return
     */
    Integer notice(String bookId);
}
