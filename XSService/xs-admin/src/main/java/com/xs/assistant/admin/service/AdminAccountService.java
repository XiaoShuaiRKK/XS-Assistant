package com.xs.assistant.admin.service;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.ResponseResult;

public interface AdminAccountService {
    ResponseResult<Boolean> createAdmin(CustomerDO customer,String key);
}
