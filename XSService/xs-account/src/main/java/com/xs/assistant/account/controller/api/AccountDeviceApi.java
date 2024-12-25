package com.xs.assistant.account.controller.api;

import com.xs.DAO.DO.system.SystemInfo;
import com.xs.DAO.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Tag(name = "AccountDeviceApi",description = "用户登录设备信息相关接口")
public interface AccountDeviceApi {
    @Operation(summary = "添加设备接口", description = "根据客户端传入的设备信息添加")
    @ApiResponse(description = "返回是否成功")
    @Parameter(description = "设备信息")
    ResponseResult<Boolean> addSystemInfo(@RequestBody SystemInfo systemInfo);

    @Operation(summary = "用户设备列表接口")
    @ApiResponse(description = "返回用户的设备信息列表",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ResponseResult.class)))
    @Parameter(description = "用户id")
    ResponseResult<List<SystemInfo>> getAllSystemInfo(@RequestParam("customer_id") String customerId);

    @Operation(summary = "更新用户设备信息",description = "根据用户的设备id更新对应的设备信息")
    @Parameter(description = "设备信息")
    ResponseResult<Boolean> updateSystemInfo(@RequestBody SystemInfo systemInfo);
}
