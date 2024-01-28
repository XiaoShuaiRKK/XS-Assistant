package com.xs.assistant.util.Impl;

import com.xs.assistant.util.IAssistantUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class DateUtil implements IAssistantUtil {

    public static String getNowStr(){
        return getNowStr("yyyy-MM-dd HH:mm:ss");
    }

    public static String getNowStr(String pattern){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 计算当天到结束时间的秒数
     * @return time
     */
    public static Long getRemainSecondsOneDay(){
        LocalDateTime midnight = LocalDateTime.now().plusDays(1)
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
        return ChronoUnit.SECONDS.between(LocalDateTime.now(),midnight);
    }
}
