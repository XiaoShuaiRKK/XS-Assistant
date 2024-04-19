package com.xs.DAO.DO.Area;

import lombok.Data;

@Data
public class Area {
    private int id;
    private String areaName;
    private String areaNameChinese;

    public Area(String areaName, String areaNameChinese) {
        this.areaName = areaName;
        this.areaNameChinese = areaNameChinese;
    }
}
