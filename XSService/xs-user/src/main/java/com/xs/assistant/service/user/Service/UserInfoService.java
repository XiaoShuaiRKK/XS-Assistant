package com.xs.assistant.service.user.Service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserInfoService {
    ResponseResult<List<CustomerDO>> getCustomers();
    ResponseResult<CustomerDO> getCustomer(Integer id);
    ResponseResult<CustomerDO> getCustomer(String numberID);
    ResponseResult<Boolean> hasCustomer(String email);
    ResponseResult<Boolean> hashCustomerByID(String accountId);
}
