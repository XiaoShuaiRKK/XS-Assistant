package com.xs.assistant.service.user.controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.service.user.service.UserUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/{v}/user")
@Slf4j
public class UserUpdateController {

    final UserUpdateService userUpdateService;

    public UserUpdateController(UserUpdateService userUpdateService) {
        this.userUpdateService = userUpdateService;
    }

    @PostMapping("/register")
    public ResponseResult<Boolean> registerCustomer(@RequestBody CustomerDO customer){
        return userUpdateService.registerCustomer(customer);
    }

    @PostMapping("/create/admin")
    public ResponseResult<Boolean> registerAdmin(@RequestBody CustomerDO customer){
        return userUpdateService.registerAdmin(customer);
    }

    @PostMapping("/upload/icon")
    public ResponseResult<Boolean> uploadIconWithCustomer(@RequestPart("icon") MultipartFile file,
                                                          @RequestParam("idNumber") String idNumber){
        boolean result = userUpdateService.uploadIcon(file, idNumber);
        return ResponseResult.success(result,result ? "上传成功" : "上传失败");
    }

    public ResponseResult<Boolean> uploadCutomerInfo(@RequestPart("icon") MultipartFile file,
                                                     @RequestBody CustomerDO customer){
        return ResponseResult.fail("功能未开发");
    }

}
