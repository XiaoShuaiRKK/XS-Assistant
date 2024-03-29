package com.xs.assistant.service.user.Controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.service.user.Service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/getCustomers")
    public ResponseResult<List<CustomerDO>> getAllCustomer(){
        return userInfoService.getCustomers();
    }

    @GetMapping("/getCustomer")
    public ResponseResult<CustomerDO> getCustomer(@RequestParam("id")Integer id){
        return userInfoService.getCustomer(id);
    }

    /**
     * 检查此邮箱是否有用户注册了
     * @param email email
     * @return true 已被注册
     */
    @GetMapping("/checkCustomer")
    public ResponseResult<Boolean> checkCustomer(@RequestParam("email")String email){
        return userInfoService.hasCustomer(email);
    }

    @GetMapping("/checkCustomer/byID")
    public ResponseResult<Boolean> checkCustomerByID(@RequestParam("accountId")String accountId){
        return userInfoService.hashCustomerByID(accountId);
    }

}
