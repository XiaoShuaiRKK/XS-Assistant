package com.xs.assistant.redis.Aspect.Annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisSet {
    String key();
    long time() default 60;
    TimeUnit timeStyle() default TimeUnit.SECONDS;
}
