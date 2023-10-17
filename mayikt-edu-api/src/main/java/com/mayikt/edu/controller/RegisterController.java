package com.mayikt.edu.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mayikt.edu.base.BaseApiController;
import com.mayikt.edu.base.BaseResponse;
import com.mayikt.edu.dto.req.EduUserReqDTO;
import com.mayikt.edu.service.RegisterService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegisterController extends BaseApiController {
    @Autowired
    private RegisterService registerService;
    /**
     * user register method
     * @param eduUserReqDTO
     * @return
     */
    @PostMapping("register")
    @ApiOperation(value = "user register method", notes = "user register")
    public BaseResponse register(@RequestBody EduUserReqDTO eduUserReqDTO){
        // verify the parameter
        String userName = eduUserReqDTO.getUserName();
        if(StringUtils.isEmpty(userName)){
            log.error("userName is null");
            return setResultError("userName is null");
        }
        String pwd = eduUserReqDTO.getPassword();
        if(StringUtils.isEmpty(pwd)){
            log.error("pwd is null");
            return setResultError("pwd is null");
        }
        // use RegisterService to register
        return registerService.register(eduUserReqDTO)
 ? setResultSuccess(): setResultError("register fail");
    }
}
