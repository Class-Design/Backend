package com.example.demo.service;

import com.example.demo.model.dataobject.UserDO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.pojo.Result;
import com.example.demo.model.pojo.UserGeneral;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
public interface UserService {
    /**
     * 注册用户
     *
     * @param userDTO
     * @return
     */
    public Result<UserGeneral> register(UserDTO userDTO);

    /**
     * 执行登录逻辑，登录成功返回 User 对象
     *
     * @param userName
     * @param pwd
     * @return
     */
    public Result<UserGeneral> login(String userName, String pwd);

    /**
     * 获取多个用户信息
     *
     * @param userIds  查询参数
     * @return
     */
    public List<UserDO> queryUser(List<Long> userIds);

    /**
     * 判断是否登录
     *
     * @param request
     * @return
     */
    public Boolean checkLogin(HttpServletRequest request);
}
