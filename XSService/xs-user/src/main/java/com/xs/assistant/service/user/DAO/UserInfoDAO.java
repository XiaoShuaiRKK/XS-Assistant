package com.xs.assistant.service.user.DAO;

import com.xs.DAO.DO.customer.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoDAO {
    List<CustomerDO> getAllCustomer();
    CustomerDO selectCustomer(@Param("id")Integer id);
    CustomerDO selectCustomerByNumberId(@Param("numberID")String numberID);
    Long selectCustomerByEmail(@Param("email")String email);
    Long selectCustomerByID(@Param("accountId")String accountId);
}
