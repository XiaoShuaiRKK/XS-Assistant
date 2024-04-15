package com.xs.assistant.service.user.Service;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserInfoService {
    List<CustomerDO> getCustomers();
    CustomerDO getCustomer(Integer id);
    CustomerDO getCustomer(String numberID);
    CustomerDO getCustomerByEmail(String email);
    Boolean hasCustomer(String email);
    Boolean hashCustomerByID(String accountId);
}
