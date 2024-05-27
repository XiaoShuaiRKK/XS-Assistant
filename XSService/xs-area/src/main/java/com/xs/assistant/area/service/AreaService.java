package com.xs.assistant.area.service;

import com.xs.DAO.DO.Area.Area;

import java.util.List;

public interface AreaService {
    List<Area> getAllArea();
    Area getArea(Integer id);
}
