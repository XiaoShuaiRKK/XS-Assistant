package com.xs.assistant.mail.service;

import com.xs.DAO.DO.customer.Mail;
import com.xs.DAO.DO.customer.MailCode;
import com.xs.assistant.mail.templates.MailTemplates;
import com.xs.assistant.util.Impl.DateUtil;
import com.xs.assistant.util.Impl.JsonUtil;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MailServiceRabbitMq {
    final MailService mailService;
    final JsonUtil jsonUtil;

    public MailServiceRabbitMq(MailService mailService, JsonUtil jsonUtil) {
        this.mailService = mailService;
        this.jsonUtil = jsonUtil;
    }

    /**
     * 发送注册成功邮件
     * @param mail 收件箱
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(name = "mailExchange",type = "topic"),
            key = "mail.register.success"))
    private void sendRegisterSuccessMail(String mail){
        Mail mailDo = new Mail();
        mailDo.setToEmail(mail);
        mailDo.setContent("恭喜您注册成功");
        mailDo.setTitle("XS-Assistant 注册成功");
        Map<String,Object> map = new HashMap<>();
        map.put("username",mail);
        map.put("date", DateUtil.getNowStr());
        mailDo.setAttachment(map);
        sendMail(mailDo, MailTemplates.TEMPLATE_REGISTER_SUCCESS_PATH);
    }

    /**
     * 发送验证码邮件
     * @param json 验证码和收件箱信息
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue,
            exchange = @Exchange(name = "mailExchange",type = "topic"),
            key = "mail.code"))
    private void sendCodeMail(String json){
        MailCode mailCode = jsonUtil.jsonToBean(json,MailCode.class);
        Map<String,Object> attachment = new HashMap<>();
        attachment.put("code",mailCode.getCode());
        attachment.put("date", DateUtil.getNowStr());
        Mail mail = new Mail();
        mail.setTitle("XS-Assistant 验证码");
        mail.setContent("请不要将你的验证码泄露给别人");
        mail.setToEmail(mailCode.getEmail());
        mail.setAttachment(attachment);
        sendMail(mail,MailTemplates.TEMPLATE_CODE_PATH);
    }

    private void sendMail(Mail mail, MailTemplates templates){
        mailService.sendHtmlMail(mail,true,templates);
    }
}
