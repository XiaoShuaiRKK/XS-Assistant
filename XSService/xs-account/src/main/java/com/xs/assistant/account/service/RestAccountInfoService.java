package com.xs.assistant.account.service;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.ResponseResult;

public interface RestAccountInfoService {
    ResponseResult<CustomerDO> getCustomerByNumberId(String id);
}
