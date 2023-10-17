package com.mayikt.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mayikt.edu.constant.MayiktConstants;
import com.mayikt.edu.core.cache.LocalCache;
import com.mayikt.edu.dto.req.EduCourseReqDTO;
import com.mayikt.edu.dto.resp.EduCourseClassRespDTO;
import com.mayikt.edu.dto.resp.EduCourseRespDTO;
import com.mayikt.edu.entity.EduCourse;
import com.mayikt.edu.mapper.EduCourseMapper;
import com.mayikt.edu.service.IEduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author kyle
 * @since 2023-10-15
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements IEduCourseService {

    @Autowired
    private EduCourseMapper eduCourseMapper;
    @Override
    public List<EduCourse> getAllEduCourse() {
        QueryWrapper<EduCourse> objectQueryWrapper = new QueryWrapper<>();
        List<EduCourse> eduCourses = eduCourseMapper.selectList(objectQueryWrapper);
        return eduCourses;
    }

    @Override
    public List<EduCourseRespDTO> searchCacheCourseList(EduCourseReqDTO eduCourseReqDTO) {
        // find all course list from cache
        List<EduCourseRespDTO> allCourseDtoList = new ArrayList<>();
        allCourseDtoList = LocalCache.get(MayiktConstants.COURSE_ALLCOURSELIST, allCourseDtoList);
        if(CollectionUtils.isEmpty(allCourseDtoList)){
            log.error("getAllCourseClass() failed to search all course class");
            return null;
        }
        // based on the cache, search the course list
        Stream<EduCourseRespDTO> stream = allCourseDtoList.stream();
        String courseName = eduCourseReqDTO.getCourseName();
        if(!StringUtils.isEmpty(courseName)){
            stream=stream.filter((c)->c.getCourseName().contains(courseName));
        }
        Integer subjectId = eduCourseReqDTO.getSubjectId();
        if(subjectId!=null){
            stream = stream.filter((c)->c.getSubjectId().equals(subjectId));
        }
        return stream.sorted(Comparator.comparing(EduCourseRespDTO::getUpdateTime))
                .collect(Collectors.toList());
    }
}
