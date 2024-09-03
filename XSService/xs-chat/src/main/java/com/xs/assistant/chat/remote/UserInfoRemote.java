package com.xs.assistant.chat.remote;

import com.xs.DAO.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(value = "XS-SERVICE-USER",path = "/xs_assistant/user")
public interface UserInfoRemote {
    @GetMapping("/checkCustomer/byID")
    ResponseResult<Boolean> checkCustomerByID(@RequestParam("accountId")String accountId);
}
