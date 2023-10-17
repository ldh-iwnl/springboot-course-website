package com.mayikt.edu.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mayikt.edu.constant.MayiktConstants;
import com.mayikt.edu.dto.req.EduUserReqDTO;
import com.mayikt.edu.entity.EduUser;
import com.mayikt.edu.mapper.EduUserMapper;
import com.mayikt.edu.service.EduUserService;
import com.mayikt.edu.service.RegisterService;
import com.mayikt.edu.utils.MD5Utils;
import com.mayikt.edu.utils.SaltUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private EduUserService eduUserService;

    @Autowired
    private EduUserMapper eduUserMapper;
    @Override
    @Transactional
    public Boolean register(EduUserReqDTO eduUserReqDTO) {
        // verify the parameter
        String userName = eduUserReqDTO.getUserName();
        if(StringUtils.isEmpty(userName)){
            log.error("userName is null");
            return Boolean.FALSE;
        }
        String pwd = eduUserReqDTO.getPassword();
        if(StringUtils.isEmpty(pwd)){
            log.error("pwd is null");
            return Boolean.FALSE;
        }
        //verify if the username is already registered
        EduUser byUserNameEduUser = eduUserService.findByUserNameEduUser(userName);
        if(byUserNameEduUser!=null){
            log.error("userName is already registered, dbUserName:{}", byUserNameEduUser.getUserName() );
            return Boolean.FALSE;
        }
        // add encryption to the password
        String salt = SaltUtils.getSalt();
        String s = MD5Utils.md5(pwd+salt);
        EduUser eduUser = new EduUser();
        eduUser.setUserName(userName);
        eduUser.setPassword(s);
        eduUser.setUserSalt(salt);

        //register (add data)

        return eduUserMapper.insert(eduUser) > MayiktConstants.DB_INSERT_RESULT_BIGZERO;
    }
}
