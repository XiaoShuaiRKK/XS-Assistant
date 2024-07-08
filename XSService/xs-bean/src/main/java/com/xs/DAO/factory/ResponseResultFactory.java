package com.xs.DAO.factory;

import com.xs.DAO.ResponseResult;

public class ResponseResultFactory {
    public static <T> ResponseResult<T>  decideResultByUpdateOrInsert(int row,T successData,T failData
            ,String successMsg,String failMsg){
        return row <= 0 ? ResponseResult.error(failData,failMsg) : ResponseResult.success(successData,successMsg);
    }
    public static <T> ResponseResult<T>  decideResultByUpdateOrInsert(boolean rs,T successData,T failData
            ,String successMsg,String failMsg){
        return rs ? ResponseResult.success(successData,successMsg) : ResponseResult.error(failData,failMsg);
    }
}
