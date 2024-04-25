package com.xs.assistant.article.Aspect;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.article.Aspect.Annotation.ResultPackage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ResultPackageAspect {

    @SuppressWarnings("all")
    @Around(value = "@annotation(resultPackage)")
    public Object packageResult(ProceedingJoinPoint joinPoint, ResultPackage resultPackage) throws Throwable {
        Object result = ((ResponseResult<Object>)joinPoint.proceed()).getData();
        return result == null ? ResponseResult.success(null,"无搜索结果") :
                ResponseResult.success(result);
    }
}
