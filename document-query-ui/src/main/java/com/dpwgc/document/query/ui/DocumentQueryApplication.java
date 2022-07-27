package com.dpwgc.document.query.ui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.dpwgc.document.query.app")
@ComponentScan("com.dpwgc.document.query.infrastructure.util")
@ComponentScan("com.dpwgc.document.query.infrastructure.util")
@ComponentScan("com.dpwgc.document.query.infrastructure.component")
@ComponentScan("com.dpwgc.document.query.infrastructure.repository")
@MapperScan("com.dpwgc.message.center.infrastructure.dal")
@SpringBootApplication
public class DocumentQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentQueryApplication.class, args);
    }

}
