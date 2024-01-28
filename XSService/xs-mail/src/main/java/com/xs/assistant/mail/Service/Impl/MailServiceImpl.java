package com.xs.assistant.mail.Service.Impl;

import com.xs.DAO.customer.DO.Mail;
import com.xs.assistant.mail.Service.MailService;
import com.xs.assistant.mail.Templates.MailTemplates;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;


@Slf4j
@Component
public class MailServiceImpl implements MailService {
    private static final String SENDER = "3631140949@qq.com";

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public void sendHtmlMail(Mail mail, boolean isShowHtml, MailTemplates templatePath) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath.getPath());
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template,mail.getAttachment());
            packMail(helper,SENDER,mail.getToEmail(),mail.getTitle(),text,true);
            javaMailSender.send(mimeMessage);
        }catch (MessagingException | IOException | TemplateException e){
            log.error(e.getMessage());
        }
    }
}
