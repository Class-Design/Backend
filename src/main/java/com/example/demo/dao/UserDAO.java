package com.example.demo.dao;

import com.example.demo.model.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
@Mapper
public interface UserDAO {
    int batchAdd(@Param("list") List<UserDO> userDOs);

    List<UserDO> findByIds(@Param("ids") List<Long> ids);

    int add(UserDO userDO);

//    int update(UserDO userDO);

    int delete(@Param("id") long id);

    List<UserDO> findAll();

    UserDO findByUserName(@Param("userName") String name);

//    List<UserDO> query(@Param("keyWord") String keyWord);
//
//    List<UserDO> search(@Param("keyWord") String keyWord, @Param("startTime") LocalDateTime startTime,
//                        @Param("endTime") LocalDateTime endTime);
}
