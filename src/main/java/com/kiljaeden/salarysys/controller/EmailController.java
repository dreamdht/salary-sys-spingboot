package com.kiljaeden.salarysys.controller;

import com.kiljaeden.salarysys.common.R;
import com.kiljaeden.salarysys.pojo.Administrators;
import com.kiljaeden.salarysys.vo.EmailVo;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Kil'jaeden
 * @Email: pure3306@163.com
 * @Date: 2023/6/29 16:25
 * @Description:
 */
@RestController
@RequestMapping("/email")
public class EmailController{
    @Resource
    JavaMailSenderImpl javaMailSender;

    SimpleMailMessage message = new SimpleMailMessage();

    @PostMapping("/sendMail")
    @Scheduled(cron = "2 * * * * *")
    public R doom(@Validated @RequestBody EmailVo emailVo){
        Date date = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        message.setSubject(emailVo.getName());
        message.setText("北京时间："+nowTime+"内容:"+emailVo.getMsg());
        message.setFrom("1850370711@qq.com");
        message.setTo("1850370711@qq.com");
        javaMailSender.send(message);
        return R.ok();
    }
}
