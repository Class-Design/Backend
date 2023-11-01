package com.example.demo.model.dto;

import com.example.demo.model.dataobject.UserDO;
import com.example.demo.model.dataobject.UserDetailDO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
@Data
public class UserDTO {
    String userName;
    String password;
    String classs;
    String name;
    String sex;
    String location;
    String mobile;
    Integer age;

    public UserDetailDO toDetailDO(){
        UserDetailDO userDetailDO=new UserDetailDO();
        BeanUtils.copyProperties(this,userDetailDO);
        return userDetailDO;
    }
}
