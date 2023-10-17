package com.mayikt.edu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.mayikt.edu.base.BaseApiController;
import com.mayikt.edu.base.BaseResponse;
import com.mayikt.edu.dto.resp.EduUserRespDTO;
import com.mayikt.edu.entity.EduUser;
import com.mayikt.edu.service.EduUserService;
import com.mayikt.edu.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class UserController extends BaseApiController {

    @Autowired
    private EduUserService eduUserService;
    @GetMapping("/getUser")
    public BaseResponse getUserByToken(@RequestHeader String token) {
        if (StringUtils.isEmpty(token))
            log.error("token is null");
        //based on the token to search for user information
        String redisValue = RedisUtils.getString(token);
        if (StringUtils.isEmpty(redisValue)) {
            log.error("token is invalid");
            return setResultError("token is invalid");
        }
        Integer userId = Integer.valueOf(redisValue);

        EduUser eduUser = eduUserService.getUserbyId(userId);
        //do to dto
        EduUserRespDTO eduUserRespDTO = BeanUtil.toBean(eduUser, EduUserRespDTO.class);
        return setResultSuccessData(eduUserRespDTO);
    }


   
}
