package com.xs.assistant.mail.service;


import com.xs.DAO.DO.customer.Mail;
import com.xs.assistant.mail.templates.MailTemplates;
import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.MimeMessageHelper;

public interface MailService {

    void sendHtmlMail(Mail mail, boolean isShowHtml, MailTemplates templatePath);

    default void packMail(MimeMessageHelper helper, String sender,
                  String[] to, String title, String text, boolean isShowHtml) throws MessagingException {
        helper.setFrom(sender);
        helper.setTo(to);
        helper.setSubject(title);
        helper.setText(text,isShowHtml);
    }
}
