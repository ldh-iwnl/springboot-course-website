package com.mayikt.edu.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.PageUtil;
import com.mayikt.edu.base.BaseApiController;
import com.mayikt.edu.base.BaseResponse;
import com.mayikt.edu.constant.MayiktConstants;
import com.mayikt.edu.core.cache.LocalCache;
import com.mayikt.edu.dto.req.EduCourseReqDTO;
import com.mayikt.edu.dto.resp.EduCourseRespDTO;
import com.mayikt.edu.entity.EduCourse;
import com.mayikt.edu.service.IEduCourseService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/course")
@ApiOperation(value = "course list", tags = "course list")
@Slf4j
public class EduCourseController extends BaseApiController {

    @Autowired
    private IEduCourseService eduCourseService;

    @PostMapping("/searchCourseList")
    @ApiOperation(value = "查询所有课程列表", notes = "查询所有课程列表")
    public BaseResponse searchCourseList(@RequestBody EduCourseReqDTO eduCourseReqDTO){
        List<EduCourseRespDTO> eduCourseRespDTOS = eduCourseService.searchCacheCourseList(eduCourseReqDTO);
        if(eduCourseRespDTOS == null){
            log.error("[based on the condition: {}, cannot find the data", eduCourseReqDTO);
            return setResultError("cannot find data in the cache");
        }
        //calculate the total number of pages
        Integer totalSize = eduCourseRespDTOS.size();
        Integer totalPage = PageUtil.totalPage(totalSize,MayiktConstants.DEFAULT_PAGE_SIZE);
        int pageNo = eduCourseReqDTO.getPageNo();
        if(!(pageNo <=totalPage)){
           log.error("pageNo is not valid {}", pageNo);
              return setResultError("pageNo is not valid");
        }
        //divide elements into pages
        List<EduCourseRespDTO> listPageEduCourses = CollUtil.page(pageNo - 1, MayiktConstants.DEFAULT_PAGE_SIZE, eduCourseRespDTOS);
        HashMap result = new HashMap<>();
        result.put("pageNo", pageNo);
        result.put("pageSize", MayiktConstants.DEFAULT_PAGE_SIZE);
        result.put("total", totalSize); // 总记录数
        result.put("totalPage", totalPage); // 总页数
        result.put("listPageEduCourses", listPageEduCourses); // result after division

        return setResultSuccessData(result);

    }
}
