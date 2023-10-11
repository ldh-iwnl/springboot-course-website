package com.mayikt.edu.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mayikt.edu.entity.EduCourseClass;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kyle
 * @since 2023-10-10
 */
public interface IEduCourseClassService extends IService<EduCourseClass> {
    /**
     * search all course class
     * @return
     */
    List<EduCourseClass> getAllCourseClass();
}
