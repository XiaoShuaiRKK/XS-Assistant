package com.xs.assistant.service.user.Service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import org.springframework.stereotype.Service;

@Service
public interface UserUpdateService {
    ResponseResult<Integer> registerCustomer(CustomerDO customer);
}
