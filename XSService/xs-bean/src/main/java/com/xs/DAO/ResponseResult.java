package com.xs.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {
    private long timestamp;
    private String status;
    private String message;
    private T data;

    public static <T> ResponseResult<T> success(){
        return success(null);
    }

    public static <T> ResponseResult<T> success(T data){
        return success(data,ResponseStatus.SUCCESS.getDescription());
    }

    public static <T> ResponseResult<T> success(T data,String message){
        return new ResponseResult<>(System.currentTimeMillis(),
                ResponseStatus.SUCCESS.getResponseCode(),
                message,
                data);
    }

    public static <T> ResponseResult<T> fail(String message){
        return fail(null,message);
    }

    public static <T> ResponseResult<T> fail(T data,String message){
        return new ResponseResult<>(System.currentTimeMillis(),
                ResponseStatus.FAIL.getResponseCode(),
                message,
                data);
    }

    public static <T> ResponseResult<T> error(String message){
        return error(null,message);
    }

    public static <T> ResponseResult<T> error(T data,String message){
        return new ResponseResult<>(System.currentTimeMillis(),
                ResponseStatus.HTTP_STATUS_400.getResponseCode(),
                message,
                data);
    }
    public static <T> ResponseResult<T> unavailable(String message){
        return unavailable(null,message);
    }

    public static <T> ResponseResult<T> unavailable(T data,String message){
        return new ResponseResult<>(System.currentTimeMillis(),
                ResponseStatus.HTTP_STATUS_503.getResponseCode(),
                message,
                data);
    }
}
