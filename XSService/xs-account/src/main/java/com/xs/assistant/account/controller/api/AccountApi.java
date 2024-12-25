package com.xs.assistant.account.controller.api;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.account.service.RestAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Tag(name = "AccountApi",description = "用户基本入口相关接口")
public interface AccountApi {
    String LOGIN_FAIL_MSG = "账号或者密码错误";

    @Operation(summary = "检查邮箱是否被注册",description = "根据邮箱查看是否有用户已经注册过")
    @ApiResponse(description = "邮费是否已被注册")
    @Parameter(name = "email",description = "邮箱")
    ResponseResult<Boolean> checkCustomer(@RequestParam("email")String email);

    @Operation(summary = "用户登录",description = "根据邮箱和密码来进行用户登录")
    @ApiResponse(description = "用户登录信息和token")
    @Parameters({
            @Parameter(name = "nameOrEmail",description = "账号或者邮箱（账号暂时不能使用）")
    })
    ResponseResult<Map<String,Object>> login(@RequestParam("nameOrEmail") @NotEmpty(message = LOGIN_FAIL_MSG)
                                             @Length(max = 30,message = LOGIN_FAIL_MSG) String name,
                                             @RequestParam("password")
                                             @NotEmpty(message = LOGIN_FAIL_MSG)
                                             @Length(max = 20,message = LOGIN_FAIL_MSG) String password);

    @Operation(summary = "发送验证码",description = "发送验证码到指定邮箱，有效期限为两分钟")
    @ApiResponse(description = "提示")
    @Parameter(name = "email",description = "邮箱")
    ResponseResult<String> sendCode(@RequestParam("email")String email);


    @Operation(summary = "注册用户",description = "根据传回来的验证码验证,将用户填写的信息进行注册")
    @ApiResponse(description = "是否注册成功")
    @Parameters({
            @Parameter(name = "code",description = "邮箱验证码")
    })
    ResponseResult<Boolean> register(@NotEmpty(message = "code cannot be empty")
                                            @Length(max = 5,min = 5,message = "Please fill in the correct verification code")
                                            @RequestParam("code")String code, @RequestBody CustomerDO customerDO);
}
