package com.xs.assistant.liked.Service;

import com.xs.DAO.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public interface LikedService {
    ResponseResult<Integer> likedByArticleId(String articleId,String accountId);
    ResponseResult<Integer> unLikedByArticleId(String articleId,String accountId);


}
