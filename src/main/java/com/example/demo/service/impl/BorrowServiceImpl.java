package com.example.demo.service.impl;

import com.example.demo.dao.BorrowDAO;
import com.example.demo.model.dataobject.BookDetailDO;
import com.example.demo.model.dataobject.BorrowDO;
import com.example.demo.model.dto.BookDTO;
import com.example.demo.model.dto.BookDetailDTO;
import com.example.demo.model.pojo.Result;
import com.example.demo.service.BorrowService;
import com.example.demo.utils.UUIDUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fireinsect
 * @create 2023/11/2
 */
@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    private RedissonClient redisson;

    @Autowired
    private BorrowDAO borrowDAO;
    RLock borrowLock = redisson.getLock("bookBorrow");
    RLock returnLock = redisson.getLock("bookReturn");

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
            result.setMessage("服务发送错误");
            result.setSuccess(false);
            return result;
        } finally {
            //解锁
            borrowLock.unlock();
        }
        result.setCode("200");
        result.setData(reserve);
        result.setMessage("书籍借出成功");
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result returnBook(BookDetailDTO bookDetailDTO, HttpServletRequest request) {
        Result result = new Result();
        //登录判断
        String userId = (String) request.getSession().getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            result.setCode("401");
            result.setMessage("尚未登录");
            result.setSuccess(false);
            return result;
        }
        if (StringUtils.isEmpty(bookDetailDTO.getBookId())||StringUtils.isEmpty(bookDetailDTO.getDetailId())){
            result.setCode("400");
            result.setMessage("未选择正确的书本");
            result.setSuccess(false);
            return result;
        }
        returnLock.lock();
        try{
            BorrowDO borrowDO=new BorrowDO();
            borrowDO.setBookId(bookDetailDTO.getBookId());
            borrowDO.setDetailId(bookDetailDTO.getDetailId());
            borrowDO.setUserId(borrowDO.getUserId());
            Integer hasReturn=borrowDAO.returBook(borrowDO);
            if (hasReturn != 1) {
                throw new RuntimeException("还书错误");
            }
        }catch (Exception e){
            result.setCode("500");
            result.setMessage("服务发送错误或未借该书");
            result.setSuccess(false);
            return result;
        }finally {
            returnLock.unlock();
        }
        result.setCode("200");
        result.setMessage("还书成功");
        result.setSuccess(true);
        return result;
    }
}
