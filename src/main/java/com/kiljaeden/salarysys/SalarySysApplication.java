package com.kiljaeden.salarysys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SalarySysApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalarySysApplication.class, args);
        log.info("项目已启动");
    }

}
