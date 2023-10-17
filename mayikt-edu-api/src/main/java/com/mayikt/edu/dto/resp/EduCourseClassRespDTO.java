package com.mayikt.edu.dto.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class EduCourseClassRespDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer Id;
    private String className;
    private Integer classParentId;
    private String url;

}
