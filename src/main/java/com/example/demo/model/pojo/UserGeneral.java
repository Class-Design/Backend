package com.example.demo.model.pojo;

import com.example.demo.model.dataobject.AuthorityDO;
import com.example.demo.model.dataobject.UserDetailDO;
import lombok.Data;

/**
 * @author fireinsect
 * @create 2023/11/1
 */
@Data
public class UserGeneral {
    UserDetailDO userDetail;
    AuthorityDO authority;

    public UserGeneral() {

    }
//    String userId;
//    String classs;
//    String name;
//    String sex;
//    String location;
//    String mobile;
//    Integer age;
//    Integer authority;
//    public UserDetail getUserDetail(UserDetailDO userDetailDO, AuthorityDO authorityDO){
//        UserDetail userDetail=new UserDetail();
//        userDetail.setUserId(userDetailDO.getUserId());
//        userDetail.setClasss(userDetailDO.getClasss());
//        userDetail.setName(userDetailDO.getName());
//        userDetail.setSex(userDetailDO.getSex());
//        userDetail.setLocation(userDetailDO.getLocation());
//        userDetail.setMobile(userDetailDO.getMobile());
//        userDetail.setAge(userDetailDO.getAge());
//        userDetail.setAuthority(authorityDO.getAuthority());
//        return userDetail;
//    }

    public UserGeneral(UserDetailDO userDetail, AuthorityDO authority){
        this.userDetail=userDetail;
        this.authority=authority;
    }
}
