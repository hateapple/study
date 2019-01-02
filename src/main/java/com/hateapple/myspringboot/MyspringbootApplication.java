package com.hateapple.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
public class MyspringbootApplication {

    public static void main(String[] args) {
        System.out.println("spring boot ok !");
        SpringApplication.run(MyspringbootApplication.class, args);
    }

}

