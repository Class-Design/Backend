package com.example.demo.service.impl;

import com.example.demo.dao.AuthorityDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.dao.UserDetailDAO;
import com.example.demo.model.dataobject.AuthorityDO;
import com.example.demo.model.dataobject.UserDO;
import com.example.demo.model.dataobject.UserDetailDO;
import com.example.demo.model.dto.AuthorityDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.pojo.Result;
import com.example.demo.model.pojo.UserGeneral;
import com.example.demo.service.UserService;
import com.example.demo.utils.UUIDUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserDetailDAO userDetailDAO;
    @Autowired
    private AuthorityDAO authorityDAO;

    @Override
    public Result updateDetail(UserGeneral userGeneral) {
        Result result=new Result();
        userDetailDAO.update(userGeneral.getUserDetail());
        authorityDAO.update(userGeneral.getAuthority());
        result.setCode("200");
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<List<UserGeneral>> getList() {
        Result<List<UserGeneral>> result=new Result<>();
        List<UserGeneral> data=new ArrayList<>();
        List<UserDetailDO> userDetailDOS=userDetailDAO.getList();
        for (UserDetailDO userDetailDO:userDetailDOS){
            UserGeneral userGeneral=new UserGeneral();
            userGeneral.setUserDetail(userDetailDO);
            userGeneral.setAuthority(authorityDAO.selectByUserId(userDetailDO.getUserId()));
            data.add(userGeneral);
        }
        result.setData(data);
        result.setCode("200");
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<UserGeneral> register(UserDTO userDTO) {
        Result<UserGeneral> result = new Result<>();

        if (StringUtils.isEmpty(userDTO.getUserName())) {
            result.setCode("601");
            result.setMessage("用户名不能为空");
            result.setSuccess(false);
            return result;
        }
        if (StringUtils.isEmpty(userDTO.getPassword())) {
            result.setCode("601");
            result.setMessage("密码不能为空");
            result.setSuccess(false);
            return result;
        }
        if(StringUtils.isEmpty(userDTO.getMobile())){
            result.setCode("601");
            result.setMessage("邮箱地址不能为空");
            result.setSuccess(false);
            return result;
        }
        UserDO userDO = userDAO.findByUserName(userDTO.getUserName());
        if (userDO != null) {
            result.setCode("602");
            result.setMessage("用户名已经存在");
            result.setSuccess(false);
            return result;
        }
        /**
         * 信息初始化
         */
        userDTO.setAge(0);
        userDTO.setClasss("未知");
        userDTO.setLocation("未知");
        userDTO.setName("新用户");
        userDTO.setSex("男");
        UserDO userDO1=userAdd(userDTO);
        UserDetailDO userDetailDO=userDTO.toDetailDO();
        userDetailDO.setUserId(userDO1.getUserId());
        userDetailAdd(userDetailDO);

        AuthorityDO authorityDO=new AuthorityDO();
        authorityDO.setUserId(userDO1.getUserId());
        authorityDO.setAuthority(1);
        authorityAdd(authorityDO);

        UserGeneral userGeneral =new UserGeneral(userDetailDO,authorityDO);

        result.setSuccess(true);
        result.setCode("200");
        result.setData(userGeneral);

        return result;
    }

    @Override
    public Result<UserGeneral> login(String userName, String pwd) {

        Result<UserGeneral> result = new Result<>();

        if (StringUtils.isEmpty(userName)) {
            result.setCode("600");
            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(pwd)) {
            result.setCode("601");
            result.setMessage("密码不能为空");
            return result;
        }

        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO == null) {
            result.setCode("602");
            result.setMessage("用户名不存在");
            return result;
        }

        // 密码加自定义盐值，确保密码安全
        String saltPwd = pwd + "_class2050";
        // 生成md5值，并转大写字母
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();

        if (!StringUtils.equals(md5Pwd, userDO.getPassword())) {
            result.setCode("603");
            result.setMessage("密码不对");
            return result;
        }
        UserGeneral userGeneral= getUserGeneral(userDO.getUserId());
        result.setSuccess(true);
        result.setCode("200");
        result.setData(userGeneral);

        return result;
    }

    @Override
    public List<UserDO> queryUser(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return null;
        }
        List<UserDO> userDOS = userDAO.findByIds(userIds);
        List<UserDO> users = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userDOS)) {
            for (UserDO userDO : userDOS) {
                users.add(userDO);
            }
        }
        return users;
    }

    @Override
    public Result<UserGeneral> getInfo(HttpServletRequest request) {
        Result<UserGeneral> result=new Result<>();
        String userId=(String)request.getSession().getAttribute("userId");
        UserDetailDO userDetailDO= userDetailDAO.selectByUserId(userId);
        AuthorityDO authorityDO=authorityDAO.selectByUserId(userId);
        UserGeneral userGeneral=new UserGeneral();
        userGeneral.setUserDetail(userDetailDO);
        userGeneral.setAuthority(authorityDO);
        result.setSuccess(true);
        result.setCode("200");
        result.setData(userGeneral);
        return result;
    }

    @Override
    public Boolean checkLogin(HttpServletRequest request) {
        Object userId = request.getSession().getAttribute("userId");
        if (userId == null) {
            return false;
        } else {
            return true;
        }

    }

    public UserGeneral getUserGeneral(String userId){
        UserGeneral userGeneral =new UserGeneral();
        userGeneral.setUserDetail(userDetailDAO.selectByUserId(userId));
        userGeneral.setAuthority(authorityDAO.selectByUserId(userId));
        return userGeneral;
    }
    public UserDO userAdd(UserDTO userDTO){
        // 密码加自定义盐值，确保密码安全
        String saltPwd = userDTO.getPassword() + "_class2050";
        // 生成md5值，并转大写字母
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();

        UserDO userDO1 = new UserDO();
        userDO1.setUserName(userDTO.getUserName());
        // 初始昵称等于用户名
        userDO1.setPassword(md5Pwd);
        userDO1.setUserId(UUIDUtils.getUUID());
        userDAO.add(userDO1);
        return userDO1;
    }
    public AuthorityDO authorityAdd(AuthorityDO authorityDO){
        authorityDAO.add(authorityDO);
        return authorityDO;
    }
    public UserDetailDO userDetailAdd(UserDetailDO userDetailDO){
        System.out.println(userDetailDO);
        userDetailDAO.add(userDetailDO);
        return userDetailDO;
    }
}
