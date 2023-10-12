package com.mayikt.edu.controller;

import com.mayikt.edu.base.BaseResponse;
import com.mayikt.edu.constant.MayiktConstants;
import com.mayikt.edu.core.cache.LocalCache;
import com.mayikt.edu.entity.EduCourseClass;
import com.mayikt.edu.service.IEduCourseClassService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
public class EduCourseClassController extends BaseApiController{

    @Autowired
    private IEduCourseClassService iEduCourseClassService;

    @GetMapping("/getAllCourseClass")
    @ApiOperation(value = "获取所有课程分类", notes = "获取所有课程分类")
    @ApiImplicitParam(paramType = "query", name = "courseId", value = "课程id", required = true, dataType = "Long")
    public BaseResponse getAllCourseClass(){
        //List<EduCourseClass> allCourseClass = iEduCourseClassService.getAllCourseClass();
        //jvm cache  to search class list
        ArrayList<Object> allCourseClass = new ArrayList<>();
        allCourseClass = LocalCache.get(MayiktConstants.COURSE_ALLCOURSECLASSLIST, allCourseClass);
        return setResultSuccessData(allCourseClass);
    }
}
