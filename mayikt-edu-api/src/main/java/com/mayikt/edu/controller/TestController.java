package com.mayikt.edu.controller;

import com.mayikt.edu.base.BaseResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mayikt.edu.base.BaseApiController;

@Slf4j
@RestController
public class TestController extends BaseApiController{

    /**
     * test springboot port 9098
     * @return
     */
    @GetMapping("/test")
    public String test() {
        return "Hello world!";
    }

    /**
     * test
     * @return
     */
    @GetMapping("/test02")
    public BaseResponse test02() {
        return setResultSuccess();
    }


    @GetMapping("/test03")
    public BaseResponse test03(Integer i) {
        Integer j=1/i;
        return setResultSuccess();
    }

    @GetMapping("/test04")
    @ApiOperation(value = "测试传递参数", notes = "测试传递参数")
    @ApiImplicitParam(paramType = "query", name = "name", value = "传递name", required = true)
    public BaseResponse test04(String name) {
        log.info("name:{}", name);
        return setResultSuccess();
    }
}
