package com.mayikt.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mayikt.edu.constant.MayiktConstants;
import com.mayikt.edu.core.cache.LocalCache;
import com.mayikt.edu.dto.resp.EduCourseClassRespDTO;
import com.mayikt.edu.dto.resp.EduCourseRespDTO;
import com.mayikt.edu.entity.EduCourse;
import com.mayikt.edu.entity.EduCourseClass;
import com.mayikt.edu.service.IEduCourseClassService;
import com.mayikt.edu.service.IEduCourseService;
import com.mayikt.edu.service.IStartPreheatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IStartPreheatServiceImpl implements IStartPreheatService {
    @Autowired
    private IEduCourseClassService eduCourseClassService;
    @Autowired
    private IEduCourseService eduCourseService;
    @Override
    public void initData() {
        Long startTime = System.currentTimeMillis();
        initEduCourseClass();
        initEduCourseList();
        Long endTime = System.currentTimeMillis();
        log.info("initData() took {} ms",endTime-startTime);
    }

    private void initEduCourseClass(){
        // search db and put it into jvm cache
        List<EduCourseClass> allCourseClass = eduCourseClassService.getAllCourseClass();
        List<EduCourseClassRespDTO> eduCourseClassRespDTOS = BeanUtil.copyToList(allCourseClass, EduCourseClassRespDTO.class);
        LocalCache.put(MayiktConstants.COURSE_ALLCOURSECLASSLIST,eduCourseClassRespDTOS);
        log.info("[初始化课程分类：]" + eduCourseClassRespDTOS);
    }

    private void initEduCourseList(){
        //save all course into jvm cache
        List<EduCourse> allEduCourse = eduCourseService.getAllEduCourse();
        List<EduCourseRespDTO> eduCourseClassRespDTOS = BeanUtil.copyToList(allEduCourse, EduCourseRespDTO.class);
        LocalCache.put(MayiktConstants.COURSE_ALLCOURSELIST,eduCourseClassRespDTOS);
        log.info("[初始化课程分类：]" + eduCourseClassRespDTOS);
    }
}
