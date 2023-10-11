package com.mayikt.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mayikt.edu.mapper")
public class AppEdu {
    public static void main(String[] args) {
        SpringApplication.run(AppEdu.class);
    }
}
