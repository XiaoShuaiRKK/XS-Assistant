package com.xs.assistant.account.controller.api;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "AccountUpdateApi",description = "用户信息更新相关接口")
public interface AccountUpdateApi {
    @Operation(summary = "上传更新用户信息",description = "将用户传进来的信息数据进行更新")
    @ApiResponse(description = "是否上传更新成功")
    @Parameters({
            @Parameter(name = "icon",description = "头像图片")
    })
    ResponseResult<Boolean> updateAccountInfo(@RequestPart("icon") MultipartFile file,
                                                     @RequestBody CustomerDO customer);

    @Operation(summary = "上传更新用户头像",description = "根据用户id将用户传入的头像图片进行更新")
    @ApiResponse(description = "是否上传更新成功")
    @Parameters({
            @Parameter(name = "icon",description = "头像"),
            @Parameter(name = "idNumber",description = "用户id")
    })
    ResponseResult<Boolean> uploadIconWithCustomer(@RequestPart("icon") MultipartFile file,
                                                          @RequestParam("idNumber") String idNumber);
}
