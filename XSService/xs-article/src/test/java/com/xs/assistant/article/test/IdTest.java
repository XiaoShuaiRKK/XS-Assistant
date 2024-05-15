package com.xs.assistant.article.test;

import com.xs.assistant.util.Impl.UIDCodeUtil;
import com.xs.assistant.util.uid.Impl.SnowflakeDistributeId;
import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void test(){
        System.out.println(System.currentTimeMillis());
    }
    @Test
    public void test1(){
        UIDCodeUtil uidCodeUtil = new UIDCodeUtil();
        String UID = uidCodeUtil.createCodeWithArticle(256,64);
        System.out.println(UID);
        System.out.println(UID.length());
    }

    @Test
    public void idTest(){
        SnowflakeDistributeId snowflakeDistributeId = new SnowflakeDistributeId(0,0);
        long start = System.currentTimeMillis();
        for(int i=0;i<10000000;i++){
            System.out.println(snowflakeDistributeId.nextId());
        }
        System.out.println("time: " + (System.currentTimeMillis() - start));
    }
}
