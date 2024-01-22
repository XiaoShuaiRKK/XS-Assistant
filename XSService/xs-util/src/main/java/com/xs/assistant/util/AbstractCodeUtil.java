package com.xs.assistant.util;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractCodeUtil {
    public abstract String createCode(int length);
}
