package com.mayikt.edu.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.mayikt.edu.base.BaseResponse;
import com.mayikt.edu.constant.MayiktConstants;
import com.mayikt.edu.core.cache.LocalCache;
import com.mayikt.edu.dto.resp.EduCourseClassRespDTO;
import com.mayikt.edu.entity.EduCourseClass;
import com.mayikt.edu.service.IEduCourseClassService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mayikt.edu.base.BaseApiController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/eduCourse") // http://localhost:9098/eduCourse
@Slf4j
@ApiOperation(value = "课程分类", notes = "课程分类")
public class EduCourseClassController extends BaseApiController{

    @Autowired
    private IEduCourseClassService iEduCourseClassService;

    @GetMapping("/getAllCourseClass")
    @ApiOperation(value = "获取所有课程分类", notes = "获取所有课程分类")
    public BaseResponse getAllCourseClass(){
        //List<EduCourseClass> allCourseClass = iEduCourseClassService.getAllCourseClass();
        //jvm cache  to search class list
        List<EduCourseClassRespDTO> allCourseClassDtoList = new ArrayList<>();
        allCourseClassDtoList = LocalCache.get(MayiktConstants.COURSE_ALLCOURSECLASSLIST, allCourseClassDtoList);
        if(CollectionUtils.isEmpty(allCourseClassDtoList)){
            log.error("getAllCourseClass() failed to search all course class");
            return setResultError("failed to search all course class");
        }
        return setResultSuccessData(allCourseClassDtoList);
    }
}
