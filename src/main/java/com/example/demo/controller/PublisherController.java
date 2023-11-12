package com.example.demo.controller;

import com.example.demo.model.pojo.Result;
import com.example.demo.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fireinsect
 * @create 2023/11/11
 */
@RestController
public class PublisherController {
    @Autowired
    PublisherService publisherService;
    @GetMapping("/publisher/get")
    public Result get(String name){
        System.out.println(name);
        return publisherService.get(name);
    }
}
