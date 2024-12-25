package com.xs.assistant.account.controller.api;

import com.xs.DAO.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "AccountPointsLevelApi",description = "用户经验等级相关接口")
public interface AccountPointsLevelApi {
    @Operation(summary = "签到经验接口",description = "根据用户传入的用户id来进行签到并进行经验加成")
    @ApiResponse(description = "是否签到成功")
    ResponseResult<Boolean> clockInPoints(@RequestParam("id_number")String idNumber);

    @Operation(summary = "检查今日签到接口",description = "根据用户id检查今日是否已经签到")
    @ApiResponse(description = "今日是否已签到")
    ResponseResult<Boolean> checkIsClockIn(@RequestParam("id_number") String idNumber);

    @Operation(summary = "增加经验接口",description = "根据用户id增加对应的经验值")
    @ApiResponse(description = "是否添加成功")
    @Parameters({
            @Parameter(name = "id_number",description = "用户id"),
            @Parameter(name = "increment_points",description = "增加的经验值")
    })
    ResponseResult<Boolean> incrementPoints(@RequestParam("id_number")String idNumber,
                                                   @RequestParam("increment_points")Integer incrementPoints);
}
