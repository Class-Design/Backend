package com.example.demo.service;

import com.example.demo.model.dataobject.UserDO;
import com.example.demo.model.dto.AuthorityDTO;
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
    public Result updateDetail(UserGeneral userGeneral);
    /**
     * 获得user列表
     * @return
     */
    public Result<List<UserGeneral>> getList();
    /**
     * 注册用户
     *
     * @param userDTO
     * @return
     */
    Result<UserGeneral> register(UserDTO userDTO);

    /**
     * 执行登录逻辑，登录成功返回 User 对象
     *
     * @param userName
     * @param pwd
     * @return
     */
    Result<UserGeneral> login(String userName, String pwd);

    /**
     * 获取多个用户信息
     *
     * @param userIds 查询参数
     * @return
     */
    List<UserDO> queryUser(List<Long> userIds);

    Result<UserGeneral> getInfo(HttpServletRequest request);

    /**
     * 判断是否登录
     *
     * @param request
     * @return
     */
    Boolean checkLogin(HttpServletRequest request);
}
