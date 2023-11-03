package com.example.demo.model.dto;

import com.example.demo.model.dataobject.ReserveDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author fireinsect
 * @create 2023/11/3
 */
@Data
public class ReserveDTO {
    String bookId;
    String userId;
    public ReserveDO toDO(){
        ReserveDO reserveDO=new ReserveDO();
        BeanUtils.copyProperties(this,reserveDO);
        return reserveDO;
    }
}
