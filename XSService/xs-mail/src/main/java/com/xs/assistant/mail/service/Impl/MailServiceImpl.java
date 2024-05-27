package com.xs.assistant.mail.service.Impl;

import com.xs.DAO.DO.customer.Mail;
import com.xs.assistant.mail.service.MailService;
import com.xs.assistant.mail.templates.MailTemplates;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;


@Slf4j
@Component
public class MailServiceImpl implements MailService {
    private static final String SENDER = "3631140949@qq.com";

    final JavaMailSender javaMailSender;
    final TemplateEngine templateEngine;
    final FreeMarkerConfigurer freeMarkerConfigurer;

    public MailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine, FreeMarkerConfigurer freeMarkerConfigurer) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    /**
     * 发送html邮件
     * @param mail 邮件
     * @param isShowHtml 是否展示html
     * @param templatePath 邮件模版(html模版)
     */
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
