package com.xs.assistant.area.DAO;

import com.xs.DAO.DO.Area.Area;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class AreaCachePool {
    private List<Area> areas;
    public List<Area> setAreas(List<Area> areas) {
        return this.areas = areas;
    }

    public boolean isEmpty(){
        return areas == null || areas.isEmpty();
    }
}
