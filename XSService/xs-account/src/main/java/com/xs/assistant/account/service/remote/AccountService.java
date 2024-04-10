package com.xs.assistant.account.service.remote;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "XS-LOGIN",path = "/xs_assistant")
public interface AccountService {
    @PostMapping("/login/customer")
    ResponseResult<CustomerDO> accountLogin(@RequestParam("nameOrEmail")String name,
                                                   @RequestParam("password")String password);
}
