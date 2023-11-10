package com.example.demo.dao;

import com.example.demo.model.dataobject.AuthorityDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
@Mapper
public interface AuthorityDAO {
    Integer add(AuthorityDO authorityDO);
    AuthorityDO selectByUserId(@Param("userId")String userId);

    Integer update(AuthorityDO authorityDO);
}
