package com.mayikt.edu.service.impl;

import com.mayikt.edu.entity.UserLoginLog;
import com.mayikt.edu.mapper.UserLoginLogMapper;
import com.mayikt.edu.service.IUserLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kyle
 * @since 2023-10-26
 */
@Service
@Slf4j
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements IUserLoginLogService {

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Override
    @Transactional
    @Async("newTaskExecutor")
    public void addUserLoginLog(UserLoginLog userLoginLog) {
        int insertResult = userLoginLogMapper.insert(userLoginLog);
        log.info("insertResult:{}",insertResult);
    }
}
