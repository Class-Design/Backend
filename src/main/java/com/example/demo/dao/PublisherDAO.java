package com.example.demo.dao;

import com.example.demo.model.dataobject.PublisherDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fireinsect
 * @create 2023/11/11
 */
@Mapper
public interface PublisherDAO {
    PublisherDO getByName(String name);
}
