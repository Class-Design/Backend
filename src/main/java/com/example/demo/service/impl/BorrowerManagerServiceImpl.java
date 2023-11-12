package com.example.demo.service.impl;

import com.example.demo.dao.BookDAO;
import com.example.demo.dao.BorrowDAO;
import com.example.demo.dao.UserDetailDAO;
import com.example.demo.model.dataobject.BorrowDO;
import com.example.demo.model.dto.BorrowDTO;
import com.example.demo.model.pojo.BorrowGeneral;
import com.example.demo.model.pojo.Result;
import com.example.demo.service.BorrowManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/11
 */
@Service
public class BorrowerManagerServiceImpl implements BorrowManagerService {
    @Autowired
    BorrowDAO borrowDAO;
    @Autowired
    UserDetailDAO userDetailDAO;
    @Autowired
    BookDAO bookDAO;
    @Override
    public Result<List<BorrowGeneral>> getList(BorrowDTO borrowDTO) {
        Result<List<BorrowGeneral>> result=new Result<>();
        List<BorrowGeneral> data=new ArrayList<>();
        for(BorrowDO borrowDO:borrowDAO.list(borrowDTO)){
            BorrowGeneral borrowGeneral=new BorrowGeneral();
            borrowGeneral.setUserInfo(userDetailDAO.selectByUserId(borrowDO.getUserId()));
            borrowGeneral.setBookInfo(bookDAO.searchByBookId(borrowDO.getBookId()));
            borrowGeneral.setDetailId(borrowDO.getDetailId());
            data.add(borrowGeneral);
        }
        result.setSuccess(true);
        result.setCode("200");
        result.setData(data);
        return result;
    }

}
