package com.xs.DAO.mapper;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.DO.customer.PointsLevel;
import com.xs.DAO.VO.customer.CustomerVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    @Mapping(source = "pointsLevel",target = "pointsLevel")
    CustomerVO toCustomerVO(CustomerDO customerDO,PointsLevel pointsLevel);
    @Mapping(source = "customerVO.pointsLevel.pointsId",target = "pointsLevelId")
    CustomerDO toCustomerDO(CustomerVO customerVO);
}
