package com.xs.assistant.liked.DAO;

import com.xs.DAO.DO.liked.LikedDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikedDAO {
    Integer batchSaveOrUpdate(@Param("likedItems") List<LikedDO> likedItems);
}
