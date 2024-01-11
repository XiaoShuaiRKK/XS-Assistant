package com.xs.assistant.service.user.Service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserInfoService {
    ResponseResult<List<CustomerDO>> getCustomers();

    ResponseResult<CustomerDO> getCustomer(Integer id);
}
