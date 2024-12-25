package com.xs.assistant.account.controller;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.account.controller.api.AccountUpdateApi;
import com.xs.assistant.account.service.RestAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@Validated
@RequestMapping("/account/upload")
public class AccountUpdateController implements AccountUpdateApi {

    final RestAccountService accountService;

    public AccountUpdateController(RestAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/customerInfo")
    public ResponseResult<Boolean> updateAccountInfo(@RequestPart("icon")MultipartFile file,
                                                     @RequestBody CustomerDO customer){
        log.info("ICON : " + file);
        log.info("CUSTOMER : " + customer);
        return ResponseResult.success(true);
    }

    @PostMapping("/icon")
    public ResponseResult<Boolean> uploadIconWithCustomer(@RequestPart("icon") MultipartFile file,
                                                          @RequestParam("idNumber") String idNumber){
        return accountService.uploadIconWithCustomer(file,idNumber);
    }
}
