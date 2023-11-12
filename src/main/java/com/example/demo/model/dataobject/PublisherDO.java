package com.example.demo.model.dataobject;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

/**
 * @author fireinsect
 * @create 2023/11/11
 */
@Data
public class PublisherDO {
    Integer id;
    String name;
    String mobile;
}
