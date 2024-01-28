package com.xs.assistant.mail.Templates;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public enum MailTemplates {
    TEMPLATE_REGISTER_SUCCESS_PATH("mailRegisterSuccess.ftl"),
    TEMPLATE_CODE_PATH("mailCode.ftl");
//    private static final List<MailTemplates> templates =
//            List.of(TEMPLATE_CODE_PATH,TEMPLATE_REGISTER_SUCCESS_PATH);
    private final String path;
}
