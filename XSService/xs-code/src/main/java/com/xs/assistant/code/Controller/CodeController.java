package com.xs.assistant.code.Controller;

import com.xs.DAO.ResponseResult;
import com.xs.assistant.code.Service.CodeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    CodeService codeService;

    @PostMapping("/send")
    public ResponseResult<Object> sendCode(@Valid @Email(message = "invalid email")
                                               @RequestParam("email")String email){
        return codeService.sendCode(email);
    }

}
