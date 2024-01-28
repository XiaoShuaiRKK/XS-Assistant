package com.xs.assistant.util.Impl;

import com.google.gson.Gson;
import com.xs.assistant.util.IAssistantUtil;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil implements IAssistantUtil {

    public String beanToJson(Object bean){
        return new Gson().toJson(bean);
    }

    public <T> T jsonToBean(String json,Class<T> clz){
        return new Gson().fromJson(json,clz);
    }

}
