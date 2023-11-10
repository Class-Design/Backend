package com.example.demo.service.impl;

import com.example.demo.dao.BookDAO;
import com.example.demo.dao.BorrowDAO;
import com.example.demo.dao.ReserveDAO;
import com.example.demo.model.dataobject.*;
import com.example.demo.model.dto.BookDTO;
import com.example.demo.model.dto.BookDetailDTO;
import com.example.demo.model.pojo.Result;
import com.example.demo.service.BorrowService;
import com.example.demo.service.ReserveService;
import com.example.demo.utils.UUIDUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/2
 */
@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private RedissonClient redisson;

    @Autowired
    private ReserveDAO reserveDAO;

    @Autowired
    private BorrowDAO borrowDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private ReserveService reserveService;
    RLock borrowLock;
    RLock returnLock;
    @PostConstruct
    private void init(){
        borrowLock = redisson.getLock("bookBorrow");
        returnLock = redisson.getLock("bookReturn");
    }
    @Override
    public Result<BookDetailDO> borrowBook(BookDTO bookDTO, HttpServletRequest request) {
        Result<BookDetailDO> result = new Result();
        BookDetailDO reserve;
        //登录判断
        String userId = (String) request.getSession().getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            result.setCode("401");
            result.setMessage("尚未登录");
            result.setSuccess(false);
            return result;
        }
        //书籍选中判断
        if (StringUtils.isEmpty(bookDTO.getBookId())) {
            result.setCode("400");
            result.setMessage("未选择正确的书本");
            result.setSuccess(false);
            return result;
        }
        //加锁
        borrowLock.lock();
        try {
            reserve = borrowDAO.bookReserve(bookDTO.getBookId());
            if (reserve != null) {
                BorrowDO borrowDO = new BorrowDO();
                borrowDO.setBookId(bookDTO.getBookId());
                borrowDO.setDetailId(reserve.getDetailId());
                borrowDO.setUserId(userId);
                Integer isGet = borrowDAO.borrow(borrowDO);
                //借出成功判断
                if (isGet != 1) {
                    throw new RuntimeException("借出错误");
                }
            }
        } catch (Exception e) {
            result.setCode("500");
            result.setMessage("服务发生错误");
            result.setSuccess(false);
            return result;
        } finally {
            //解锁
            borrowLock.unlock();
        }
        ReserveDO reserveDO=new ReserveDO(bookDTO.getBookId(),userId);
        reserveDAO.delete(reserveDO);
        result.setCode("200");
        result.setData(reserve);
        result.setMessage("书籍借出成功");
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result returnBook(MyBorrowDTO myBorrowDTO, HttpServletRequest request) {
        Result result = new Result();
        //登录判断
        String userId = (String) request.getSession().getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            result.setCode("401");
            result.setMessage("尚未登录");
            result.setSuccess(false);
            return result;
        }
        if (StringUtils.isEmpty(myBorrowDTO.getBookInfo().getBookId())||StringUtils.isEmpty(myBorrowDTO.getDetailId())){
            result.setCode("400");
            result.setMessage("未选择正确的书本");
            result.setSuccess(false);
            return result;
        }
        returnLock.lock();
        try{
            BorrowDO borrowDO=new BorrowDO();
            borrowDO.setBookId(myBorrowDTO.getBookInfo().getBookId());
            borrowDO.setDetailId(myBorrowDTO.getDetailId());
            borrowDO.setUserId(userId);
            Integer hasReturn=borrowDAO.returBook(borrowDO);
            if (hasReturn != 1) {
                throw new RuntimeException("还书错误");
            }
        }catch (Exception e){
            result.setCode("500");
            result.setMessage("服务发生错误或未借该书");
            result.setSuccess(false);
            return result;
        }finally {
            returnLock.unlock();
        }
        reserveService.notice(myBorrowDTO.getBookInfo().getBookId());
        result.setCode("200");
        result.setMessage("还书成功");
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<MyBorrowDTO>> myBorrow(HttpServletRequest request) {
        Result<List<MyBorrowDTO>> result = new Result();
        //登录判断
        String userId = (String) request.getSession().getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            result.setCode("401");
            result.setMessage("尚未登录");
            result.setSuccess(false);
            return result;
        }
        List<BorrowDO> borrowDOS=borrowDAO.searchByUserId(userId);
        List<MyBorrowDTO> myBorrowDTOS=new ArrayList<>();
        for (BorrowDO borrowDO:borrowDOS){
            MyBorrowDTO myBorrowDTO=new MyBorrowDTO();
            myBorrowDTO.setDetailId(borrowDO.getDetailId());
            myBorrowDTO.setBookInfo(bookDAO.searchByBookId(borrowDO.getBookId()));
            System.out.println(myBorrowDTO.getBookInfo());
            myBorrowDTOS.add(myBorrowDTO);
        }
        result.setSuccess(true);
        result.setCode("200");
        result.setData(myBorrowDTOS);
        return result;
    }

    @Override
    public Result reserveBook(BookDTO bookDTO, HttpServletRequest request) {
        Result result=new Result();
        String userId = (String) request.getSession().getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            result.setCode("401");
            result.setMessage("尚未登录");
            result.setSuccess(false);
            return result;
        }
        ReserveDO reserveDO=new ReserveDO();
        reserveDO.setBookId(bookDTO.getBookId());
        reserveDO.setUserId(userId);
        try{
            reserveDAO.add(reserveDO);
        }catch (Exception e){
            result.setCode("501");
            result.setMessage("用户已预定");
            result.setSuccess(false);
            return result;
        }
        result.setSuccess(true);
        result.setCode("200");
        return result;
    }
}
