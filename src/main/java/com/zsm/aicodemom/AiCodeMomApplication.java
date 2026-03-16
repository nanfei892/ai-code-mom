package com.zsm.aicodemom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.zsm.aicodemom.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class AiCodeMomApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCodeMomApplication.class, args);
    }

}
