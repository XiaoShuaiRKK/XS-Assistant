package com.xs.assistant.util;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public abstract class AbstractCodeUtil {
    public abstract String createCode(long count);
}
