package com.xs.assistant.service.user.service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import org.springframework.stereotype.Service;

@Service
public interface UserUpdateService {
    ResponseResult<Boolean> registerCustomer(CustomerDO customer);
    ResponseResult<Boolean> registerAdmin(CustomerDO customer);
}
