package com.xs.assistant.login.DAO;

import com.xs.DAO.customer.DO.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginDAO {
    Integer login(@Param("nameOrEmail")String name,
                     @Param("password")String password);
}
