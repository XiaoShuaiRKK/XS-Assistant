package com.xs.assistant.liked.service.remote;

import com.xs.DAO.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "XS-SERVICE-USER",path = "/xs_assistant/user")
public interface AccountRemoteService {
    @GetMapping("/checkCustomer/byID")
    ResponseResult<Boolean> checkCustomerByID(@RequestParam("accountId")String accountId);
}
