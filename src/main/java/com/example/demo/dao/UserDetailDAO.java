package com.example.demo.dao;

import com.example.demo.model.dataobject.UserDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
@Mapper
public interface UserDetailDAO {
    UserDetailDO selectByUserId(@Param("userId") String userId);
    Integer add(UserDetailDO userDetailDO);
}
