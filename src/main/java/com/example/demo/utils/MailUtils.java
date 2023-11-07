package com.example.demo.utils;

import com.example.demo.dao.ReserveDAO;
import com.example.demo.dao.UserDetailDAO;
import com.example.demo.model.dataobject.ReserveDO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MailUtils {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private ReserveDAO reserveDAO;

    @Resource
    private UserDetailDAO userDetailDAO;

    public static MailUtils mailUtils;

    @PostConstruct
    public void init(){
        mailUtils =this;
        mailUtils.mailSender=this.mailSender;
        mailUtils.reserveDAO=this.reserveDAO;
        mailUtils.userDetailDAO=this.userDetailDAO;
    }


    public static void sendMails(String bookId){

        List<ReserveDO> reserveDOLists= mailUtils.reserveDAO.searchByBookId(bookId);
        List<String> emails=new ArrayList<>();

        for(ReserveDO reserveDO: reserveDOLists){
            String id=reserveDO.getUserId();
            String email= mailUtils.userDetailDAO.selectByUserId(id).getMobile();
            emails.add(email);
        }

        for(String email:emails){
            new Thread(){
                @Override
                public void run() {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom("3212761752@qq.com");
                    message.setTo(email);            // 设置收件人邮箱
                    message.setSubject("图书预约通知");                  // 设置邮件主题
                    message.setText("您预约的图书已被归还，请及时前往借阅！"); // 设置邮件文本内容
                    message.setSentDate(new Date());                // 设置邮件发送时间
                    //发送
                    mailUtils.mailSender.send(message);
                    super.run();
                }
            }.start();
        }

    }
}
