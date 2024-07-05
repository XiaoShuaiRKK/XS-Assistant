package com.xs.assistant.service.user.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xs.DAO.DO.customer.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<CustomerDO> {
    @Select("SELECT Id,FirstName,LastName,Email,Birth,IdNumber,AreaId,StateId,Level FROM customer")
    IPage<CustomerDO> selectPage(Page<CustomerDO> page);
    @Select("SELECT count(*) FROM customer")
    Long selectAllCount();
}
