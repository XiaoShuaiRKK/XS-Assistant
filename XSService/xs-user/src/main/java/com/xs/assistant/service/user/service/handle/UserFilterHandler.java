package com.xs.assistant.service.user.service.handle;

import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.assistant.redis.filter.FilterFactory;
import com.xs.assistant.redis.filter.RedisFilter;
import com.xs.assistant.redis.util.RedisUtil;
import org.springframework.stereotype.Component;

@Component
public class UserFilterHandler {
    final RedisUtil redisUtil;

    private static final String FILTER_ID_NUMBER_KEY = "customer:id-number:bloom-filter";
    private static final String FILTER_EMAIL_KEY = "customer:email:bloom-filter";
    private static final String FILTER_NAME_KEY = "customer:name:bloom-filter";
    final RedisFilter idNumberFilter;
    final RedisFilter emailFilter;
    final RedisFilter nameFilter;

    public UserFilterHandler(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
        this.idNumberFilter = FilterFactory.createDefaultFilter(FilterFactory.FilterType.BLOOM,redisUtil,FILTER_ID_NUMBER_KEY);
        this.emailFilter = FilterFactory.createDefaultFilter(FilterFactory.FilterType.BLOOM,redisUtil,FILTER_EMAIL_KEY);
        this.nameFilter = FilterFactory.createDefaultFilter(FilterFactory.FilterType.BLOOM,redisUtil,FILTER_NAME_KEY);
    }

    public void addUserToFilter(CustomerDO customer){
        idNumberFilter.add(customer.getIdNumber());
        emailFilter.add(customer.getEmail());
        nameFilter.add(customer.getFirstName() + " " + customer.getLastName());
    }

    public Boolean checkIfUserExistsByIdNumber(String idNumber){
        return idNumberFilter.contains(idNumber);
    }

    public Boolean checkIfUserExistsByEmail(String email){
        return emailFilter.contains(email);
    }

    public Boolean checkIfUserExistsByName(String firstName, String lastName){
        return nameFilter.contains(firstName + " " + lastName);
    }

    public Boolean checkIfUserExists(CustomerDO customer){
        return checkIfUserExistsByIdNumber(customer.getIdNumber()) ||
                checkIfUserExistsByEmail(customer.getEmail()) ||
                checkIfUserExistsByName(customer.getFirstName(),customer.getLastName());
    }

}
