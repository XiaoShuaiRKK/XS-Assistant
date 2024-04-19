package com.xs.assistant.area.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xs.DAO.DO.Area.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AreaRepository extends BaseMapper<Area> {
    @Select("SELECT Id,AreaName,AreaNameChinese FROM area")
    List<Area> selectAll();
    @Select("SELECT Id,AreaName,AreaNameChinese FROM area WHERE Id = (#{id})")
    Area selectAreaById(Integer id);
}
