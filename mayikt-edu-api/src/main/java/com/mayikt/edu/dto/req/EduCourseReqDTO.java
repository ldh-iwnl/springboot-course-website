package com.mayikt.edu.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EduCourseReqDTO {
    @ApiModelProperty("课程名称")
    private String courseName;



    @ApiModelProperty("课程专业ID")
    private Integer subjectId;


    // divided into pages
    private Integer pageNo;

}
