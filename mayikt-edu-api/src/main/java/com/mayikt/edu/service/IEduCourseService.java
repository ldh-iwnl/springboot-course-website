package com.mayikt.edu.service;

import com.mayikt.edu.dto.req.EduCourseReqDTO;
import com.mayikt.edu.dto.resp.EduCourseRespDTO;
import com.mayikt.edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程表 服务类
 * </p>
 *
 * @author kyle
 * @since 2023-10-15
 */
public interface IEduCourseService extends IService<EduCourse> {
    /***
     * cache into jvm when startup and then use it
     */

    List<EduCourse> getAllEduCourse();

    List<EduCourseRespDTO> searchCacheCourseList(EduCourseReqDTO eduCourseReqDTO);
}
