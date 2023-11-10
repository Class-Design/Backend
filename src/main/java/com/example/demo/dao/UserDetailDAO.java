package com.example.demo.dao;

import com.example.demo.model.dataobject.UserDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
@Mapper
public interface UserDetailDAO {
    UserDetailDO selectByUserId(@Param("userId") String userId);
    Integer add(UserDetailDO userDetailDO);

    List<UserDetailDO> getList();

    Integer update(UserDetailDO userDetailDO);
}
