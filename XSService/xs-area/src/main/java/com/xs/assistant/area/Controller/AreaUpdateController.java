package com.xs.assistant.area.Controller;

import com.netflix.discovery.converters.Auto;
import com.xs.DAO.DO.Area.Area;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.area.Service.AreaUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
@Slf4j
public class AreaUpdateController {

    @Autowired
    AreaUpdateService areaUpdateService;

    @PostMapping("/upload")
    public ResponseResult<List<Area>> importExcel(@RequestParam("file")MultipartFile excel){
        return ResponseResult.success(areaUpdateService.importExcel(excel),"已经存在");
    }
}
