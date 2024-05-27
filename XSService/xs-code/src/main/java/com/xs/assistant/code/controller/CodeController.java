package com.xs.assistant.code.controller;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.code.service.CodeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class CodeController {
    final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    /**
     * 发送验证码
     * @param email 电子邮箱
     * @return 提示信息
     */
    @PostMapping("/send")
    public ResponseResult<String> sendCode(@Valid @Email(message = "invalid email")
                                               @RequestParam("email")String email){
        return codeService.sendCode(email);
    }

    /**
     * 发送注册成功信息到对应邮箱
     * @param email 电子邮箱
     * @return 提示信息
     */
    @PostMapping("/register/success")
    public ResponseResult<String> sendRegisterSuccess(@RequestParam("email")String email){
        return codeService.sendRegisterSuccess(email);
    }

    @PostMapping("/check")
    public ResponseResult<Boolean> checkCode(@RequestParam("code")String code,@RequestParam("email")String email){
        return codeService.checkCode(code,email);
    }
}
