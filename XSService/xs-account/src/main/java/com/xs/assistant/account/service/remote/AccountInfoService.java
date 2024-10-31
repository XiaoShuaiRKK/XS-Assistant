package com.xs.assistant.account.service.remote;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.account.service.fallback.UserInfoFallback;
import jakarta.ws.rs.core.MediaType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@FeignClient(value = "XS-SERVICE-USER",path = "/xs_assistant",fallback = UserInfoFallback.class)
public interface AccountInfoService {
    @GetMapping("/user/getCustomers")
    ResponseResult<List<CustomerDO>> getCustomers(@RequestParam("page")Integer page,
                                                          @RequestParam("size")Integer size);
    @GetMapping("/user/getCustomer")
    ResponseResult<CustomerDO> getCustomer(@RequestParam("id")Integer id);
    @GetMapping("/user/getCustomer/byNumberID")
    ResponseResult<CustomerDO> getCustomerByNumberId(@RequestParam("ID")String id);
    @GetMapping("/user/getCustomer/byEmail")
    ResponseResult<CustomerDO> getCustomerByEmail(@RequestParam("email")String email);
    @GetMapping("/user/checkCustomer")
    ResponseResult<Boolean> checkCustomer(@RequestParam("email")String email);
    @PostMapping("/user/register")
    ResponseResult<Boolean> registerCustomer(@RequestBody CustomerDO customer);
    @PostMapping(value = "/user/upload/icon",consumes = MediaType.MULTIPART_FORM_DATA)
    ResponseResult<Boolean> uploadIconWithCustomer(@RequestPart("icon") MultipartFile file,
                                                          @RequestParam("idNumber") String idNumber);
}
