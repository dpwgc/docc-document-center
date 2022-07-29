package com.dpwgc.document.center.ui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.dpwgc.document.center.app")
@ComponentScan("com.dpwgc.document.center.infrastructure.assembler")
@ComponentScan("com.dpwgc.document.center.infrastructure.util")
@ComponentScan("com.dpwgc.document.center.infrastructure.component")
@ComponentScan("com.dpwgc.document.center.infrastructure.repository")
@ComponentScan("com.dpwgc.document.center.infrastructure.dal")

// Mybatis-plus Mapper扫描，infrastructure.dal.document是ES数据源，不用扫描
@MapperScan("com.dpwgc.document.center.infrastructure.dal.category.mapper")

@SpringBootApplication
public class DocumentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentCenterApplication.class, args);
    }

}
