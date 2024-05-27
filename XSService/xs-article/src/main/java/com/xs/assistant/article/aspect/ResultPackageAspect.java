package com.xs.assistant.article.aspect;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.aspect.annotation.ResultPackage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 包装
 */
@Aspect
@Component
public class ResultPackageAspect {

    /**
     * 对数据进行统一包装
     * @param joinPoint
     * @param resultPackage
     * @return
     * @throws Throwable
     */
    @SuppressWarnings("all")
    @Around(value = "@annotation(resultPackage)")
    public Object packageResult(ProceedingJoinPoint joinPoint, ResultPackage resultPackage) throws Throwable {
        Object result = ((ResponseResult<Object>)joinPoint.proceed()).getData();
        return result == null ? ResponseResult.success(null,"无搜索结果") :
                ResponseResult.success(result);
    }
}
