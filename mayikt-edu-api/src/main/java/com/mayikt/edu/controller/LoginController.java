package com.mayikt.edu.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mayikt.edu.base.BaseApiController;
import com.mayikt.edu.base.BaseResponse;
import com.mayikt.edu.dto.req.EduUserReqDTO;
import com.mayikt.edu.entity.EduUser;
import com.mayikt.edu.service.EduUserService;
import com.mayikt.edu.utils.MD5Utils;
import com.mayikt.edu.utils.RedisUtils;
import com.mayikt.edu.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
public class LoginController extends BaseApiController {
    @Autowired
    private EduUserService eduUserService;

    /**
     *  user login method
     * @param eduUserReqDTO
     * @return
     */

    @PostMapping("/login")
    @ApiOperation(value = "user login method", notes = "user login")
    public BaseResponse login(@RequestBody EduUserReqDTO eduUserReqDTO){
        //verify the parameter
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
        //search for user information
        EduUser byUserNameEduUser = eduUserService.findByUserNameEduUser(userName);
        if(byUserNameEduUser==null){
            log.error("userName is not exist");
            return setResultError("user is not exist or pwd is wrong");
        }
        String dbPwd = byUserNameEduUser.getPassword();
        String userSalt = byUserNameEduUser.getUserSalt();
        String newPwd = MD5Utils.md5(pwd + userSalt);
        if(!newPwd.equals(dbPwd)){
            log.error("pwd is wrong");
            return setResultError("user is not exist or pwd is wrong");
        }
        // generate token
        String userToken = TokenUtils.getToken();
        RedisUtils.setString(userToken, byUserNameEduUser.getUserId());
        HashMap<String, String> result = new HashMap<>();
        result.put("userToken", userToken);
        log.info("token:{}", userToken);
        return setResultSuccessData(result);
    }
}
