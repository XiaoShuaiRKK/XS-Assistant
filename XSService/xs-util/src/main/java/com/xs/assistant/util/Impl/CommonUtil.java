package com.xs.assistant.util.Impl;

import com.xs.assistant.util.AbstractCodeUtil;
import com.xs.assistant.util.IAssistantUtil;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CommonUtil extends AbstractCodeUtil implements IAssistantUtil {

    @Override
    public String createCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < length;i++)
            sb.append(random.nextInt(10));
        return sb.toString();
    }
}
