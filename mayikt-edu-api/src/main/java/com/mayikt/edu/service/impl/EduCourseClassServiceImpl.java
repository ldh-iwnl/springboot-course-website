package com.mayikt.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.edu.entity.EduCourseClass;
import com.mayikt.edu.dto.resp.EduCourseClassRespDTO;
import com.mayikt.edu.mapper.EduCourseClassMapper;
import com.mayikt.edu.service.IEduCourseClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kyle
 * @since 2023-10-10
 */
@Service
public class EduCourseClassServiceImpl extends ServiceImpl<EduCourseClassMapper,EduCourseClass> implements IEduCourseClassService {
    @Autowired
    private EduCourseClassMapper eduCourseClassMapper;
    @Override
    public List<EduCourseClass> getAllCourseClass() {
        QueryWrapper<EduCourseClass> objectQueryWrapper = new QueryWrapper<>();
        List<EduCourseClass> eduCourseClasses = eduCourseClassMapper.selectList(objectQueryWrapper);
        return eduCourseClasses;
    }
}
