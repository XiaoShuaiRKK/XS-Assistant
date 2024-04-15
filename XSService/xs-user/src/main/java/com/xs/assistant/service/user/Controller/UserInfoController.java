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
        return packageResult(userInfoService.getCustomers());
    }

    @GetMapping("/getCustomer")
    public ResponseResult<CustomerDO> getCustomer(@RequestParam("id")Integer id){
        return packageResult(userInfoService.getCustomer(id));
    }

    @GetMapping("/getCustomer/ByNumberID")
    public ResponseResult<CustomerDO> getCustomerByNumberId(@RequestParam("ID")String id){
        return packageResult(userInfoService.getCustomer(id));
    }

    /**
     * 检查此邮箱是否有用户注册了
     * @param email email
     * @return true 已被注册
     */
    @GetMapping("/checkCustomer")
    public ResponseResult<Boolean> checkCustomer(@RequestParam("email")String email){
        return packageResult(userInfoService.hasCustomer(email));
    }

    @GetMapping("/checkCustomer/byID")
    public ResponseResult<Boolean> checkCustomerByID(@RequestParam("accountId")String accountId){
        return packageResult(userInfoService.hashCustomerByID(accountId));
    }

    private <T> ResponseResult<T> packageResult(T data){
        return data == null ? ResponseResult.fail(null,"无查询结果") : ResponseResult.success(data);
    }

}
