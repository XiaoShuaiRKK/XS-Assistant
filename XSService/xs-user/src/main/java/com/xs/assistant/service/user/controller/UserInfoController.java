package com.xs.assistant.service.user.controller;

import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.service.user.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController {
    final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/getCustomers")
    public ResponseResult<List<CustomerDO>> getCustomers(@RequestParam("page")Integer page,
                                                          @RequestParam("size")Integer size){
        return packageResult(userInfoService.getCustomers(page,size).getRecords());
    }

    @GetMapping("/getCustomer")
    public ResponseResult<CustomerDO> getCustomer(@RequestParam("id")Integer id){
        return packageResult(userInfoService.getCustomer(id));
    }

    @GetMapping("/getCustomer/byNumberID")
    public ResponseResult<CustomerDO> getCustomerByNumberId(@RequestParam("ID")String id){
        return packageResult(userInfoService.getCustomer(id));
    }

    @GetMapping("/getCustomer/byEmail")
    public ResponseResult<CustomerDO> getCustomerByEmail(@RequestParam("email")String email){
        return packageResult(userInfoService.getCustomerByEmail(email));
    }

    /**
     * 检查此邮箱是否有用户注册了
     * @param email email
     * @return true 已被注册
     */
    @GetMapping("/checkCustomer")
    public ResponseResult<Boolean> checkCustomer(@RequestParam("email")String email){
        return Boolean.TRUE.equals(userInfoService.hasCustomer(email)) ? ResponseResult.success(true) :
                ResponseResult.fail(false,"无此用户");
//        return packageResult(userInfoService.hasCustomer(email));
    }

    @GetMapping("/getCustomer/name")
    public ResponseResult<String> getCustomerName(@RequestParam("idNumber")String idNumber){
        CustomerDO customer = userInfoService.getCustomer(idNumber);
        return ResponseResult.success(customer.getFirstName() + " " + customer.getLastName());
    }

    @GetMapping("/checkCustomer/byID")
    public ResponseResult<Boolean> checkCustomerByID(@RequestParam("accountId")String accountId){
        return packageResult(userInfoService.hashCustomerByID(accountId));
    }

    private <T> ResponseResult<T> packageResult(T data){
        return data == null ? ResponseResult.fail(null,"无查询结果") : ResponseResult.success(data);
    }

}
