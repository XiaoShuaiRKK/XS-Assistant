package com.xs.assistant.util.Impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xs.assistant.util.IAssistantUtil;
import com.xs.assistant.util.config.LocalDateTimeAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

@Component
public class JsonUtil implements IAssistantUtil {

    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
            new LocalDateTimeAdapter()).create();

    public String beanToJson(Object bean){
        return gson.toJson(bean);
    }

    public <T> T jsonToBean(String json,Class<T> clz){
        return gson.fromJson(json,clz);
    }

    public <T> T jsonToBean(String json, Type type){
        return gson.fromJson(json,type);
    }

}
