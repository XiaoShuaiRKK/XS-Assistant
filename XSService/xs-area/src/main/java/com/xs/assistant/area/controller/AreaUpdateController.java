package com.xs.assistant.area.controller;

import com.xs.DAO.DO.Area.Area;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.area.service.AreaUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
@Slf4j
public class AreaUpdateController {
    final AreaUpdateService areaUpdateService;

    public AreaUpdateController(AreaUpdateService areaUpdateService) {
        this.areaUpdateService = areaUpdateService;
    }

    /**
     * 使用excel导入国家信息
     * (不推荐使用)
     * @param excel excel
     * @return 国家列表
     */
    @PostMapping("/upload")
    public ResponseResult<List<Area>> importExcel(@RequestParam("file")MultipartFile excel){
        return ResponseResult.success(areaUpdateService.importExcel(excel),"已经存在");
    }
}
