package com.xs.assistant.area.controller;

import com.xs.DAO.DO.Area.Area;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.area.service.AreaService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
@Slf4j
@Validated
public class AreaController {
    final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    /**
     * 获取所有国家的信息
     * @return 国家列表
     */
    @GetMapping("/getAll")
    @CircuitBreaker(name = "area-breaker-api", fallbackMethod = "timeoutHandler")
    @Retry(name = "area-select-api",fallbackMethod = "timeoutHandler")
    public ResponseResult<List<Area>> getAreaAll(){
        return ResponseResult.success(areaService.getAllArea());
    }

    /**
     * 根据id获取国家的信息
     * @param id id
     * @return 国家信息
     */
    @GetMapping("/get")
    @CircuitBreaker(name = "area-breaker-api", fallbackMethod = "timeoutHandler")
    @Retry(name = "area-select-api",fallbackMethod = "timeoutHandler")
    public ResponseResult<Area> getArea(@Min(value = 1,message = "Please enter the correct value")
                                            @RequestParam("id")Integer id){
        return ResponseResult.success(areaService.getArea(id));
    }

    private <T> ResponseResult<T> timeoutHandler(Exception e){
        log.info("Level Down");
        log.error(e.getMessage());
        return ResponseResult.unavailable("系统繁忙请稍后再试" + Thread.currentThread().getName());
    }
}
