package com.xs.assistant.login.DAO;

import com.xs.DAO.DO.customer.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginDAO {
    CustomerDO login(@Param("nameOrEmail")String name);
    String getCustomerPassword(@Param("email")String email);
}
