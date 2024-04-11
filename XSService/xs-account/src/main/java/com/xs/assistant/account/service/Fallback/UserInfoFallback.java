package com.xs.assistant.account.service.Fallback;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.account.service.remote.AccountInfoService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserInfoFallback implements AccountInfoService {

    @Override
    public ResponseResult<List<CustomerDO>> getAllCustomer() {
        return ResponseResult.unavailable("用户信息系统正忙");
    }

    @Override
    public ResponseResult<CustomerDO> getCustomer(Integer id) {
        return ResponseResult.unavailable("用户信息系统正忙");
    }

    @Override
    public ResponseResult<CustomerDO> getCustomerByNumberId(String id) {
        return ResponseResult.unavailable("用户信息系统正忙");
    }

    @Override
    public ResponseResult<Boolean> checkCustomer(String email) {
        return ResponseResult.unavailable("用户信息系统正忙");
    }

    @Override
    public ResponseResult<Integer> registerCustomer(CustomerDO customer) {
        return ResponseResult.unavailable("用户信息系统正忙");
    }
}
