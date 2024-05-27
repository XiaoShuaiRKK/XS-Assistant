package com.xs.assistant.mail.templates;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MailTemplates {
    TEMPLATE_REGISTER_SUCCESS_PATH("mailRegisterSuccess.ftl"),
    TEMPLATE_CODE_PATH("mailCode.ftl");
    private final String path;
}
