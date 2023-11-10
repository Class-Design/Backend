package com.example.demo.service.impl;

import com.example.demo.dao.BorrowDAO;
import com.example.demo.dao.ReserveDAO;
import com.example.demo.model.dataobject.ReserveDO;
import com.example.demo.model.dto.ReserveDTO;
import com.example.demo.model.pojo.Result;
import com.example.demo.service.ReserveService;
import com.example.demo.utils.MailUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/3
 */
@Service
public class ReserveServiceimpl implements ReserveService {
    @Autowired
    ReserveDAO reserveDAO;
    @Autowired
    BorrowDAO borrowDAO;
    @Override
    public Result reserve(ReserveDTO reserveDTO, HttpServletRequest request) {
        Result result=new Result();
        String userId = (String) request.getSession().getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            result.setCode("401");
            result.setMessage("尚未登录");
            result.setSuccess(false);
            return result;
        }
        if (StringUtils.isEmpty(reserveDTO.getBookId())){
            result.setCode("400");
            result.setMessage("未选择正确的书");
            result.setSuccess(false);
            return result;
        }
        if (borrowDAO.bookReserve(reserveDTO.getBookId())!=null){
            result.setCode("400");
            result.setMessage("该书当前可以借出");
            result.setSuccess(false);
            return result;
        }
        reserveDTO.setUserId(userId);
        Integer get=reserveDAO.add(reserveDTO.toDO());
        if (get!=1){
            result.setCode("400");
            result.setMessage("该书已经预定");
            result.setSuccess(false);
            return result;
        }
        result.setCode("200");
        result.setMessage("预定成功");
        result.setSuccess(true);
        return result;
    }

    @Override
    public Integer notice(String bookId) {
        return MailUtils.sendMails(bookId);
    }
}
