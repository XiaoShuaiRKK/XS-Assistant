package com.xs.assistant.account.Advice;

import com.xs.DAO.ResponseResult;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

@RestControllerAdvice
@Slf4j
public class ValidationAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> ResponseResult<T> handler(Exception e){
        log.error(e.getMessage());
        return ResponseResult.fail("服务异常,请稍后再试");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public <T> ResponseResult<T> handler(ConstraintViolationException e){
        StringBuffer errorMsg = new StringBuffer();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        violations.forEach(x -> errorMsg.append(x.getMessage()).append(";"));
        return ResponseResult.fail(errorMsg.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public <T> ResponseResult<T> handler(MethodArgumentNotValidException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        return ResponseResult.success(null,fieldError.getDefaultMessage());
    }
}
