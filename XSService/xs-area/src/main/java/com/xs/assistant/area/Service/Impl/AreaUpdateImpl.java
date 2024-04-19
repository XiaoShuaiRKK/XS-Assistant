package com.xs.assistant.area.Service.Impl;

import com.xs.DAO.DO.Area.Area;
import com.xs.assistant.area.DAO.AreaCachePool;
import com.xs.assistant.area.DAO.AreaUpdateRepository;
import com.xs.assistant.area.Service.AreaUpdateService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class AreaUpdateImpl implements AreaUpdateService {
    @Autowired
    AreaUpdateRepository areaUpdateRepository;
    @Autowired
    AreaCachePool areaCachePool;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Area> importExcel(MultipartFile file) {
        return null;
//        List<Area> areas = new ArrayList<>();
//        
//        try(InputStream inputStream = file.getInputStream()) {
//            Workbook workbook = WorkbookFactory.create(inputStream);
//            Sheet sheet = workbook.getSheetAt(0);
//            for(int rowIndex = 0;rowIndex <= sheet.getLastRowNum();rowIndex++){
//                Row row = sheet.getRow(rowIndex);
//                if(row != null){
//                    Cell cell = row.getCell(0);
//                    Cell cell1 = row.getCell(1);
//                    if(cell != null){
//                        switch (cell.getCellType()){
//                            case STRING -> areas.add(new Area(cell.getStringCellValue(),cell1.getStringCellValue()));
//                            case NUMERIC -> areas.add(new Area(String.valueOf(cell.getNumericCellValue()),
//                                    String.valueOf(cell1.getNumericCellValue())));
//                        }
//                    }
//                }
//            }
//            workbook.close();
//            areaUpdateRepository.importLoad(areas);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return areas;
    }
}
