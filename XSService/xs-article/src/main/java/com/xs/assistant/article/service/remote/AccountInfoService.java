package com.xs.assistant.article.service.remote;

import com.xs.DAO.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "XS-SERVICE-USER",path = "/xs_assistant/user")
public interface AccountInfoService {
    @GetMapping("/getCustomer/name")
    ResponseResult<String> getCustomerName(@RequestParam("idNumber")String idNumber);
}
