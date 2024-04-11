package com.xs.assistant.account.service.Impl;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.ResponseResult;
import com.xs.assistant.account.service.RestAccountInfoService;
import com.xs.assistant.account.service.remote.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestAccountInfoServiceImpl implements RestAccountInfoService {
    @Autowired
    AccountInfoService accountInfoService;

    @Override
    public ResponseResult<CustomerDO> getCustomerByNumberId(String id) {
        return accountInfoService.getCustomerByNumberId(id);
    }
}
