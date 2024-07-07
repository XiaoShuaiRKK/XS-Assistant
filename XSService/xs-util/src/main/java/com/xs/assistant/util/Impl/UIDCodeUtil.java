package com.xs.assistant.util.Impl;

import com.xs.assistant.util.AbstractCodeUtil;
import com.xs.assistant.util.IAssistantUtil;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class UIDCodeUtil extends AbstractCodeUtil implements IAssistantUtil {

    static final char[] CHARS = {'0','A','B','C','D','E','F','G','H','I','J','K','L','M','N',
            'O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    private static final int DEFAULT_CODE_SIZE = 64;

    @Override
    public String createCode(long count) {
        return CreateCodeFactory.getCreateCodeInstance(CreateCodeType.ACCOUNT).createCode(count,14);
    }

    public String createCode(CreateCodeType codeType,long count){
        return CreateCodeFactory.getCreateCodeInstance(codeType).createCode(count,DEFAULT_CODE_SIZE);
    }

    public String createCode(CreateCodeType codeType,long count,int size){
        return CreateCodeFactory.getCreateCodeInstance(codeType).createCode(count,size);
    }

//    public String createCode(String count){
//        return createCode(4,Integer.parseInt(count));
//    }

//    public String createCode(Long count){
//        return createCode(4, Math.toIntExact(count));
//    }
//
//    @Override
//    public String createCode(int count) {
//        return createCode(4,count);
//
//    }

//    public String createCode(int length,int count){
//        StringBuilder code = new StringBuilder("XS");
//        code.append(DateUtil.getNowStr("yyyyMMdd"));
//        StringBuilder c = new StringBuilder(String.valueOf(count));
//        if(c.length() <= length){
//            int n = length - c.length();
//            for(int i=0;i<n;i++)
//                c.insert(0,"0");
//        }else {
//            c = new StringBuilder(createSpCode(count));
//        }
//        code.append(c);
//        return code.toString();
//    }


    public interface CreateCode{
        String createCode(long count,int size);
    }

    public enum CreateCodeType{
        ACCOUNT,
        ARTICLE,
        CHANNEL
    }

    private static class CreateCodeFactory{
        private CreateCodeFactory(){}
        static Map<CreateCodeType,CreateCode> createCodeMap = new EnumMap<>(CreateCodeType.class);
        static {
            createCodeMap.put(CreateCodeType.ACCOUNT,((count, size) -> {
                String date = DateUtil.getNowStr("yyyyMMdd");
                String id = createSpCode((int) count);
                return "XS" + date + "0".repeat(Math.max(0,size - id.length() - 10)) + id;
            }));
            createCodeMap.put(CreateCodeType.ARTICLE,(count,size) -> createSnowflakeDistributeCode("XSA",count,size));
            createCodeMap.put(CreateCodeType.CHANNEL,(count, size) -> createSnowflakeDistributeCode("XSC",count,size));
        }
        public static CreateCode getCreateCodeInstance(CreateCodeType codeType){
            return createCodeMap.get(codeType);
        }

        /**
         * 生成id算法
         * @param count 多出来的人数如 15202 - 9999 = 5203 则生成出来的后四位则为00RC
         * @return 后位ID
         */
        private static String createSpCode(int count){
            StringBuilder sb = new StringBuilder();
            int[] nums = {0,0,0,0};
            int c = count - 9999;
            if(c <= 26)
                nums[3] = c;
            else {
                int pre = 0;
                int i = 3;
                do {
                    pre = c / 26;
                    nums[i] = c % 26;
                    c = pre;
                    i--;
                }while (pre > 26);
            }
            for(int value:nums)
                sb.append(CHARS[value]);
            return sb.toString();
        }

        private static String createSnowflakeDistributeCode(String pre,long count,int size){
            String articleCount = String.valueOf(count);
            return pre + "0".repeat(Math.max(0, size - articleCount.length())) + count;
        }
    }

//    public String createCodeWithArticle(long articleCount){
//        return createCodeWithArticle(articleCount,11);
//    }
//
//    public String createCodeWithArticle(long articleCount,int size){
//        String count = String.valueOf(articleCount);
//        return "XSA" + "0".repeat(Math.max(0, size - count.length())) +
//                count;
//    }
}
