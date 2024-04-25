package com.xs.assistant.util.Impl;

import com.xs.assistant.util.IAssistantUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class BeanFieldUtil implements IAssistantUtil {
    public <T> String[] getFieldsName(Class<T> tClass){
        Field[] fields = tClass.getDeclaredFields();
        String[] fieldsName = new String[fields.length];
        for(int i=0;i < fields.length;i++)
            fieldsName[i] = fields[i].getName();
        return fieldsName;
    }

    public <T> List<String> getFieldsNameArray(Class<T> tClass){
        return List.of(getFieldsName(tClass));
    }
}
