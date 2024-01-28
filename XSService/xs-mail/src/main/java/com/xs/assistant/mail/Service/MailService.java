package com.xs.assistant.mail.Service;


import com.xs.DAO.customer.DO.Mail;
import com.xs.assistant.mail.Templates.MailTemplates;
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
