package com.xs.assistant.admin.service.remote;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "XS-SERVICE-USER",path = "/xs_assistant")
public interface AccountService {
    @PostMapping("/create/admin")
    ResponseResult<Boolean> registerAdmin(@RequestBody CustomerDO customer);
}
