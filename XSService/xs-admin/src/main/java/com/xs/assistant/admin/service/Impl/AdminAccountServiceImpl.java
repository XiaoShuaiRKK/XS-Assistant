package com.xs.assistant.admin.service.Impl;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.admin.service.AdminAccountService;
import com.xs.assistant.admin.service.remote.AccountService;
import com.xs.assistant.admin.service.remote.EncryptionRemoteService;
import org.springframework.stereotype.Service;

@Service
public class AdminAccountServiceImpl implements AdminAccountService {
    private static final String PRIVATE_KEY = "588fcdaba46f49f31fcaabb4927ace7c30238054ab7a7130e918aac8e8bfc256e3e97bd2d0a28cc9";

    final EncryptionRemoteService encryptionRemoteService;
    final AccountService accountService;

    public AdminAccountServiceImpl(EncryptionRemoteService encryptionRemoteService, AccountService accountService) {
        this.encryptionRemoteService = encryptionRemoteService;
        this.accountService = accountService;
    }


    @Override
    public ResponseResult<Boolean> createAdmin(CustomerDO customer, String key) {
        if(encryptionRemoteService.checkEncodePassword(key,PRIVATE_KEY))
            return ResponseResult.success(false,"密钥错误");
        return accountService.registerAdmin(customer);
    }
}
