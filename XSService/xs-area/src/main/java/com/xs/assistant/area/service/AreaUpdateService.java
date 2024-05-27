package com.xs.assistant.area.service;

import com.xs.DAO.DO.Area.Area;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AreaUpdateService {
    List<Area> importExcel(MultipartFile file);
}
