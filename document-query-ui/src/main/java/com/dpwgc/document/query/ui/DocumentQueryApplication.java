package com.dpwgc.document.query.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.dpwgc.document.query.app")
@SpringBootApplication
public class DocumentQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentQueryApplication.class, args);
    }

}
