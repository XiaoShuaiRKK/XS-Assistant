package com.xs.assistant.account.service.remote;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "XS-SERVICE-USER",path = "/xs_assistant")
public interface AccountInfoService {
    @GetMapping("/user/getCustomers")
    ResponseResult<List<CustomerDO>> getAllCustomer();

    @GetMapping("/user/getCustomer")
    ResponseResult<CustomerDO> getCustomer(@RequestParam("id")Integer id);

    @GetMapping("/user/checkCustomer")
    ResponseResult<Boolean> checkCustomer(@RequestParam("email")String email);

    @PostMapping("/user/register")
    ResponseResult<Integer> registerCustomer(@RequestBody CustomerDO customer);
}
