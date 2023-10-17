package com.mayikt.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mayikt.edu.entity.EduUser;
import com.mayikt.edu.mapper.EduUserMapper;
import com.mayikt.edu.service.EduUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EduUserServiceImpl implements EduUserService {
    @Autowired
    private EduUserMapper eduUserMapper;
    @Override
    public EduUser findByUserNameEduUser(String userName) {

        LambdaQueryWrapper<EduUser> eduLambdaQueryWrapper = new LambdaQueryWrapper<>();
        eduLambdaQueryWrapper.eq(EduUser::getUserName,userName);
        EduUser eduUser = eduUserMapper.selectOne(eduLambdaQueryWrapper);
        return eduUser;
    }

    @Override
    public EduUser getUserbyId(Integer userId) {
        return eduUserMapper.selectById(userId);
    }
}
