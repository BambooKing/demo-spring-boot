package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.demo.RegexDemo.testa;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {


        SpringApplication.run(DemoApplication.class, args);
        testa();
//        testb();
//        getStringNoBlank();
    }


}
