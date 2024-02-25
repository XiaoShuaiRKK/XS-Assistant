package com.xs.assistant.service.user.DAO;

import com.xs.DAO.customer.DO.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface UserInfoDAO {
    List<CustomerDO> getAllCustomer();
    CustomerDO selectCustomer(@Param("id")Integer id);
    Integer selectCustomerByEmail(@Param("email")String email);
}
