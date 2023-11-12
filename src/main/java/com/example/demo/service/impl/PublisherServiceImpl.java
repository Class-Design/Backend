package com.example.demo.service.impl;

import com.example.demo.dao.PublisherDAO;
import com.example.demo.model.dataobject.PublisherDO;
import com.example.demo.model.pojo.Result;
import com.example.demo.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fireinsect
 * @create 2023/11/11
 */
@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    PublisherDAO publisherDAO;

    @Override
    public Result<PublisherDO> get(String name) {
        Result<PublisherDO> result=new Result<>();
        result.setData(publisherDAO.getByName(name));
        result.setCode("200");
        result.setSuccess(true);
        return result;
    }
}
