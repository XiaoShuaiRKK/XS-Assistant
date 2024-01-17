package com.xs.assistant.account.service.remote;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.customer.DO.CustomerDO;
import com.xs.assistant.account.service.Fallback.UserInfoFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "XS-LOGIN",path = "/xs_assistant")
public interface AccountService {
    @PostMapping("/login/customer")
    ResponseResult<Integer> accountLogin(@RequestParam("nameOrEmail")String name,
                                                   @RequestParam("password")String password);
}
