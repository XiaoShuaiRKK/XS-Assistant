package com.xs.assistant.area.Controller;

import com.xs.DAO.DO.Area.Area;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.area.Service.AreaService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    AreaService areaService;
    @GetMapping("/getAll")
    @CircuitBreaker(name = "area-breaker-api", fallbackMethod = "timeoutHandler")
    @Retry(name = "area-select-api",fallbackMethod = "timeoutHandler")
    public ResponseResult<List<Area>> getAreaAll(){
        return ResponseResult.success(areaService.getAllArea());
    }

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
