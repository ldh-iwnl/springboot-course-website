package com.mayikt.edu.dto.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("edu_user")
@ApiModel(value = "EduUser对象", description = "")
@Data
public class EduUserReqDTO{

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;


}
