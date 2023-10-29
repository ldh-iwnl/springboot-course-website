package com.mayikt.edu.service;

import com.mayikt.edu.entity.UserLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kyle
 * @since 2023-10-26
 */
public interface IUserLoginLogService extends IService<UserLoginLog> {
    /**
     * add user login log
     */
    void addUserLoginLog(UserLoginLog userLoginLog);

}
