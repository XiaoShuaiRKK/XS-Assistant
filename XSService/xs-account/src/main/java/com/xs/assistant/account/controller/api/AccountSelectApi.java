package com.xs.assistant.account.controller.api;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.DO.customer.PointsLevel;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.VO.customer.CustomerVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "AccountSelectApi",description = "用户信息查询相关接口")
public interface AccountSelectApi {
    @Operation(summary = "分页获取用户信息列表",description = "分页获取用户信息列表")
    @ApiResponse(description = "用户信息列表")
    @Parameters({
            @Parameter(name = "page",description = "页数"),
            @Parameter(name = "size",description = "一页的大小")
    })
    ResponseResult<List<CustomerDO>> getAccounts(@Min(value = 1,message = "Please enter the correct value")
                                                        @RequestParam("page")Integer page,
                                                        @Min(value = 1,message = "Please enter the correct value")
                                                        @RequestParam("size")Integer size);

    @Operation(summary = "用户信息查询接口",description = "根据用户id查询对应用户的信息数据")
    @ApiResponse(description = "用户信息数据")
    @Parameter(name = "id",description = "用户id")
    ResponseResult<CustomerVO> getAccountById(@Length(min = 14,max = 14,message = "The Number ID format error")
                                                     @RequestParam("id")String id);

    @Operation(summary = "用户信息查询接口(邮箱)",description = "根据邮箱查询对应用户的信息数据")
    @ApiResponse(description = "用户信息数据")
    @Parameter(name = "email",description = "邮箱")
    ResponseResult<CustomerDO> getAccountByEmail(@Email(message = "invalid email")
                                                        @RequestParam("email")String email);

    @Operation(summary = "用户名字查询接口",description = "根据用户id查询对应用户的名字")
    @ApiResponse(description = "名字")
    @Parameter(name = "id",description = "用户id")
    ResponseResult<String> getAccountNameById(@RequestParam("id")String id);
}
