package com.xs.assistant.area.DAO;

import com.xs.DAO.DO.Area.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AreaUpdateRepository {
    Integer importLoad(@Param("areas") List<Area> areas);
}
