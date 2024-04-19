package com.xs.assistant.area.Service;

import com.xs.DAO.DO.Area.Area;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface AreaUpdateService {
    List<Area> importExcel(MultipartFile file);
}
