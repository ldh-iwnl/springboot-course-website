package com.mayikt.edu.service.impl;

import com.mayikt.edu.constant.MayiktConstants;
import com.mayikt.edu.core.cache.LocalCache;
import com.mayikt.edu.entity.EduCourseClass;
import com.mayikt.edu.service.IEduCourseClassService;
import com.mayikt.edu.service.IStartPreheatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mayikt.edu.constant.MayiktConstants.COURSE_ALLCOURSECLASSLIST;
@Slf4j
@Service
public class IStartPreheatServiceImpl implements IStartPreheatService {
    @Autowired
    private IEduCourseClassService eduCourseClassService;
    @Override
    public void initData() {
        Long startTime = System.currentTimeMillis();
        initEduCourseClass();
        Long endTime = System.currentTimeMillis();
        log.info("initData() took {} ms",endTime-startTime);
    }

    private void initEduCourseClass(){
        // search db and put it into jvm cache
        List<EduCourseClass> allCourseClass = eduCourseClassService.getAllCourseClass();
        LocalCache.put(MayiktConstants.COURSE_ALLCOURSECLASSLIST,allCourseClass);
    }
}
