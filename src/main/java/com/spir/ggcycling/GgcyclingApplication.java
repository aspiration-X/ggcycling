package com.spir.ggcycling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication()
@MapperScan("com.spir.ggcycling.dao")
public class GgcyclingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GgcyclingApplication.class, args);
    }

}
