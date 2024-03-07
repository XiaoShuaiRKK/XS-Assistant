package com.xs.assistant.liked.Service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.article.VO.ArticleVO;
import com.xs.DAO.liked.DO.LikedDO;

import java.util.List;

public interface LikedTopService {
    ResponseResult<List<LikedDO>> getTop();
    ResponseResult<List<LikedDO>> getTop(Long count);
}
