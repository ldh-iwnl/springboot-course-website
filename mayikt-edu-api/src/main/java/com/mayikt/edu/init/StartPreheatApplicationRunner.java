package com.mayikt.edu.init;

import com.mayikt.edu.service.IEduCourseClassService;
import com.mayikt.edu.service.impl.IStartPreheatServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * startup preheat
 */
@Component
@Slf4j
public class StartPreheatApplicationRunner implements ApplicationRunner{

    @Autowired
    private IStartPreheatServiceImpl iStartPreheatService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("startup preheat");
        // initialize the JVM cache
        iStartPreheatService.initData();
        log.info("startup preheat finished");

    }
}