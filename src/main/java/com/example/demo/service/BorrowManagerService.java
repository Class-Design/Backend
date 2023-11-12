package com.example.demo.service;

import com.example.demo.model.dto.BorrowDTO;
import com.example.demo.model.pojo.BorrowGeneral;
import com.example.demo.model.pojo.Result;

import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/11
 */
public interface BorrowManagerService {
    Result<List<BorrowGeneral>> getList(BorrowDTO borrowDTO);

}
