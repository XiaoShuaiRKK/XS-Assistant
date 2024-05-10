package com.xs.assistant.liked.service.abstracts;

import com.xs.DAO.ResponseResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractLikedService {
    protected static final String LIKED_KEY = "liked:";

    protected static final String REDIS_LIKED_KEY = "TotalLiked";

    protected ResponseResult<Integer> fallLiked(Exception e){
        log.error(e.getMessage());
        return ResponseResult.fail("服务器繁忙，请稍后重试");
    }

    protected ResponseResult<Integer> hasLikedByAccount(){
        return likedResult("已点过赞");
    }
    protected ResponseResult<Integer> hasUnLikedByAccount(){
        return likedResult("未点过赞");
    }
    protected ResponseResult<Integer> successLiked(String msg){
        return ResponseResult.success(1,msg);
    }
    protected ResponseResult<Integer> userNotExist(){
        return ResponseResult.success(0,"User does not exist");
    }

    private ResponseResult<Integer> likedResult(String msg){
        return ResponseResult.success(0,msg);
    }
}
