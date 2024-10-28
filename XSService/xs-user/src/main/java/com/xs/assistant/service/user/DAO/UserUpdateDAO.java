package com.xs.assistant.service.user.DAO;

import com.xs.DAO.DO.customer.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserUpdateDAO {
    Integer insertCustomer(@Param("customer") CustomerDO customerDO);
    Integer insertAdmin(@Param("customer")CustomerDO customer);
    Integer uploadIcon(@Param("icon")String iconPath,@Param("idOfNumber")String idOfNumber);
}
