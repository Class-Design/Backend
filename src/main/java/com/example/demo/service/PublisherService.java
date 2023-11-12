package com.example.demo.service;

import com.example.demo.model.dataobject.PublisherDO;
import com.example.demo.model.pojo.Result;

/**
 * @author fireinsect
 * @create 2023/11/11
 */
public interface PublisherService {
    Result<PublisherDO> get(String name);
}
