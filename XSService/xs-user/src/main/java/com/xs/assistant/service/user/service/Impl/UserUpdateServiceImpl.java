package com.xs.assistant.service.user.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xs.DAO.ResponseResult;
import com.xs.DAO.DO.customer.CustomerDO;
import com.xs.DAO.option.AccountLevelEnum;
import com.xs.assistant.redis.filter.FilterFactory;
import com.xs.assistant.redis.filter.RedisFilter;
import com.xs.assistant.redis.util.RedisUtil;
import com.xs.assistant.service.user.DAO.UserMapper;
import com.xs.assistant.service.user.DAO.UserUpdateDAO;
import com.xs.assistant.service.user.remote.EncryptionService;
import com.xs.assistant.service.user.service.PointsLevelService;
import com.xs.assistant.service.user.service.UserUpdateService;
import com.xs.assistant.service.user.service.handle.UserFilterHandler;
import com.xs.assistant.service.user.service.remote.ImageFileService;
import com.xs.assistant.util.function.RedisKeyFunction;
import com.xs.assistant.util.Impl.UIDCodeUtil;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Service
@Slf4j
public class UserUpdateServiceImpl implements UserUpdateService {

    private static final String REGISTER_COUNT_TODAY_KEY = "registerCount";

    final UserUpdateDAO userUpdateDAO;
    final UserMapper userMapper;
    final EncryptionService encryptionService;
    final ImageFileService imageFileService;
    final PointsLevelService pointsLevelService;
    @Autowired
    RedisUtil redisUtil;
    final UIDCodeUtil uidCodeUtil;
    final UserFilterHandler userFilterHandler;
    final RedisFilter clockInFilter;

    private final RedisKeyFunction<String,Long> registerHas = (key,value) -> redisUtil.increment(key,1L);

    public UserUpdateServiceImpl(UserUpdateDAO userUpdateDAO, UserMapper userMapper, EncryptionService encryptionService, ImageFileService imageFileService,
                                 PointsLevelService pointsLevelService, UIDCodeUtil uidCodeUtil, UserFilterHandler userFilterHandler) {
        this.userUpdateDAO = userUpdateDAO;
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
        this.imageFileService = imageFileService;
        this.pointsLevelService = pointsLevelService;
        this.uidCodeUtil = uidCodeUtil;
        this.userFilterHandler = userFilterHandler;
        this.clockInFilter = FilterFactory.createDefaultFilter(FilterFactory.FilterType.BLOOM,redisUtil,"customer:clock-in:bloom-filter");
    }

    @PostConstruct
    public void initSync(){
        updateUserToFilter();
    }

    @Override
    @Retry(name = "user-customer-register-api",fallbackMethod = "systemFailHandler")
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> registerCustomer(CustomerDO customer) {
        boolean rs = registerAccount(customer, AccountLevelEnum.ACCOUNT.ordinal());
        //将用户id放入布隆过滤器
        userFilterHandler.addUserToFilter(customer);
        return ResponseResult.success(rs,rs ? "注册失败" : "注册成功");
    }

    @Override
    public ResponseResult<Boolean> registerAdmin(CustomerDO customer) {
        boolean rs = registerAccount(customer, AccountLevelEnum.ADMIN.ordinal());
        return ResponseResult.success(rs,rs ? "注册失败" : "注册成功");
    }

    @Override
    public Boolean uploadIcon(MultipartFile file, String idNumber) {
        log.info(idNumber + " : upload icon");
        ResponseResult<String> uploadRes = imageFileService.uploadFile(file);
        String fileName = uploadRes.getData();
        if(fileName == null){
            return false;
        }
        ResponseResult<String> previewedRes = imageFileService.previewFile(fileName);
        Integer rs = userUpdateDAO.uploadIcon(previewedRes.getData(), idNumber);
        return rs > 0;
    }

    @Override
    public Boolean updateCustomer(MultipartFile icon, CustomerDO customer) {
        return null;
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void syncUserToBloomFilter(){
        updateUserToFilter();
    }

    private void updateUserToFilter(){
        log.info("Sync Update Customer To Bloom Filter");
        int currentPage = 1;
        Page<CustomerDO> page = new Page<>(currentPage,1000);
        while (true){
            log.info("Page {}",currentPage);
            page.setCurrent(currentPage);
            List<CustomerDO> customerDOList = userMapper.selectPage(page,null).getRecords();
            if(customerDOList == null || customerDOList.isEmpty()){
                break;
            }
            customerDOList.forEach(userFilterHandler::addUserToFilter);
            currentPage++;
        }
    }

    private boolean registerAccount(CustomerDO customer,Integer level){
        long count;
        //设置用户权限
        customer.setLevel(level);
        //将用户密码进行加盐加密
        customer.setPassword(encryptionService.getEncodePassword(customer.getPassword()));
        registerHas.hasKey(redisUtil.hasKey(REGISTER_COUNT_TODAY_KEY),((k,u) -> redisUtil.increment(k,u)))
                .execute(REGISTER_COUNT_TODAY_KEY, userMapper.selectAllCount());
        count = redisUtil.increment(REGISTER_COUNT_TODAY_KEY);
        //设置用户唯一的id
        customer.setIdNumber(uidCodeUtil.createCode(count));
        //设置用户经验等级信息
        String pointsLevel = pointsLevelService.createPointsLevel(customer.getIdNumber());
        customer.setPointsLevelId(pointsLevel);
        return userUpdateDAO.insertCustomer(customer) <= 0;
    }

    private <T extends Serializable> ResponseResult<T> systemFailHandler(Exception e){
        log.error(e.getMessage());
        return ResponseResult.error("系统繁忙,请稍后重试");
    }
}
