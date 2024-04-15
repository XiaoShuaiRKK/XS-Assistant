package com.xs.assistant.redis.Aspect.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisSetHash {
    String keyName();
    String key();
    long time() default 60;
    TimeUnit timeStyle() default TimeUnit.SECONDS;
}
