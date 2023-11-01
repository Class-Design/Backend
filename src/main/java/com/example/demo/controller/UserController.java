package com.example.demo.controller;

import com.example.demo.dao.UserDAO;
import com.example.demo.dao.UserDetailDAO;
import com.example.demo.model.dataobject.UserDO;
import com.example.demo.model.dataobject.UserDetailDO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.pojo.Paging;
import com.example.demo.model.pojo.Result;
import com.example.demo.model.pojo.UserGeneral;
import com.example.demo.service.UserService;
import com.example.demo.utils.UUIDUtils;
import com.github.pagehelper.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
/**
 * @author fireinsect
 * @create 2023/11/1
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailDAO userDetailDAO;
//    @GetMapping("/users")
//    public Result<Paging<UserDO>> getAll(@RequestParam(value = "pageNum", required = false) Integer pageNum,
//                                         @RequestParam(value = "pageSize", required = false) Integer pageSize) {
//        Result<Paging<UserDO>> result = new Result();
//
//        if (pageNum == null) {
//            pageNum = 1;
//        }
//        if (pageSize == null) {
//            pageSize = 15;
//        }
//        // 设置当前页数为1，以及每页3条记录
//        Page<UserDO> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> userDAO.findAll());
//
//        result.setSuccess(true);
//        result.setData(
//                new Paging<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page.getResult()));
//        return result;
//    }
//    @PostMapping("/user")
//    @ResponseBody
//    public UserDO save(@RequestBody UserDO userDO) {
//        userDAO.add(userDO);
//        return userDO;
//    }
//    @PostMapping("/user/batchAdd")
//    @ResponseBody
//    public List<UserDO> batchAdd(@RequestBody List<UserDO> userDOs) {
//        userDAO.batchAdd(userDOs);
//        return userDOs;
//    }
//    @PostMapping("/user/update")
//    @ResponseBody
//    public UserDO update(@RequestBody UserDO userDO) {
//        userDAO.update(userDO);
//        return userDO;
//    }
//    @GetMapping("/user/del")
//    @ResponseBody
//    public boolean delete(@RequestParam("id") Long id) {
//        return userDAO.delete(id) > 0;
//    }
//
//    @GetMapping("/user/findByUserName")
//    @ResponseBody
//    public UserDO findByUserName(@RequestParam("userName") String userName) {
//        return userDAO.findByUserName(userName);
//    }
//
//    @GetMapping("/user/search")
//    @ResponseBody
//    public List<UserDO> search(@RequestParam("keyWord") String keyWord,
//                               @RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//                               LocalDateTime startTime,
//                               @RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//                               LocalDateTime endTime) {
//        return userDAO.search(keyWord, startTime, endTime);
//    }
//
//    @GetMapping("/user/findByIds")
//    @ResponseBody
//    public List<UserDO> findByIds(@RequestParam("ids") List<Long> ids) {
//        return userDAO.findByIds(ids);
//    }
//
    @PostMapping("/user/register")
    public Result<UserGeneral> register(@RequestBody UserDTO user){
        return userService.register(user);
    }
    @PostMapping("/user/login")
    public Result<UserGeneral> login(@RequestBody UserDTO user , HttpServletRequest request){
        Result<UserGeneral> result = userService.login(user.getUserName(), user.getPassword());
        if (result.isSuccess()) {
            request.getSession().setAttribute("userId", result.getData().getUserDetail().getUserId());
        }
        return result;
    }
    @GetMapping("/user/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request) {
        Result result = new Result();
        request.getSession().removeAttribute("userId");

        result.setSuccess(true);
        return result;
    }
    @GetMapping("/user/checklogin")
    public Result<Boolean> login(HttpServletRequest request){
        Result result = new Result();

        result.setSuccess(true);
        result.setData(userService.checkLogin(request));
        return result;
    }
}
