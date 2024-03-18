package com.xs.assistant.liked.Service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.liked.LikedDO;

import java.util.List;

public interface LikedTopService {
    ResponseResult<List<LikedDO>> getTop();
    ResponseResult<List<LikedDO>> getTop(Long count);
}
